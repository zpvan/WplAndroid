# SendMy

[Send My: Arbitrary data transmission via Apple's Find My network](https://positive.security/blog/send-my)

通过Apple's Find My network传输Arbitrary data



* It's possible to **upload arbitrary data** from **non-internet-connected devices** by sending Find My BLE **broadcasts to nearby Apple devices** that then **upload the data for you**

附近的Apple设备组件成Find My网络，通过BLE广播能将**Arbitrary Data**由未连接的设备**上传**。

* We released an **ESP32 firmware** that turns the micocontroller into an (upload only) modem, and a **macOS application** to retrieve, decode and display the uploaded data: https://github.com/positive-security/send-my

Positive-security发布ESP32固件（模拟AirTag），还发布一个macOS application用来解析、解码和展示上传的数据。

* Being inherent to the privacy and security-focused design of the Find My Offline Finding system, it seems **unlikely that this misuse can be prevented completely**

Find My Offline Finding System以安全隐私为设计核心，Offline System滥用看起来不能被完全阻止。

<img src="send_my_1_architure.png">



## Introduce

With the recent release of Apple's AirTags, I was curious whether Find My's Offline Finding network could be (ab)used to upload arbitrary data to the Internet, from devices that are not connected to WiFi or mobile internet. The data would be broadcasted via Bluetooth Low Energy and picked up by nearby Apple devices, that, once they are connected to the Internet, forward the data to Apple servers where it could later be retrieved from. Such a technique could be employed by small sensors in uncontrolled environments to avoid the cost and power-consumption of mobile internet. It could also be interesting for exfiltrating data from Faraday-shielded sites that are occasionally visited by iPhone users.

Find My Offline Finding network用于（不能连接wifi或移动数据网络的）设备上传Arbitrary Data的场景。数据通过BLE广播散播，由附近的Apple devices收集，一旦这些Apple devices连上网络，随后向Apple server上传。Offline设备不仅可以指**丢失设备**，还可能是指**Nearby Devices**，这些技术都依赖于一颗脱离“大核”的“小sensor”（节能）。IPhone与AirTags都配备这样的“小sensor”。

In theory this should be possible: If you can emulate two AirTags, you can encode data by activating only one of the two AirTags at a specific point in time. The receiving device could then check which AirTag is active at what time and decode this back to the original data. However, such a scheme seems highly unreliable and probably unusable in real-world situations due to its very low bandwidth (especially with [restriction such as 16 AirTags per Apple ID](https://9to5mac.com/guides/airtag/) it seemed like data transfer could be limited to only a few bits per hour).

即使能模拟两个AirTags，但同一时刻只能通过编码激活其中一个。Receiving devices能够检查出已激活AirTag的激活时间与解码出原始数据。（没看懂这段表达的是什么意思）

Therefore, the feasibility of the idea depends on the system's design and implementation. It turned out that security and privacy decisions in the design of the Offline Finding mechanism make our "use case" quite efficient and almost impossible to protect against.

因此，idea的可行性取决于系统的设计与实现。证明了Offline Finding mechanism的隐私安全设计使得我们的“用例”十分有效，几乎不可能防范。（假的Apple devices利用了该Offline Finding网络，也无法被Apple侦查出？）

## Offline Finding network description

Thanksfully, the protocol has already been extensively reverse engineered by a group of TU Darmstadt, that published the paper ["Who Can *Find My* Devices?](https://arxiv.org/pdf/2103.02282.pdf)" in March 2021 and released a proof-of-concept open source implementation called [OpenHaystack](https://github.com/seemoo-lab/openhaystack), which allows you to create your own accessories that are tracked by Apple's Find My network. Huge credits to the team! Their work made this possible and both our PoC firmware and the Mac application are based on OpenHaystack.

Apple Find My Network的协议已经被破解且发布，其实现在Github上也有对应的项目OpenHaystack（可以自定义创建配件，从而利用Find My Network进行追踪），牛批呀！

A bit simplified, the Find My Offline Finding system works like this:

1. When paring an AirTag with an Apple Device, an Elliptic Curve key pair is collaboratively generated with the public key remaining on the AirTag (and a shared secret to generate rolling public keys)

当AirTag与Apple Device做Pairing时，生成椭圆曲线秘钥对，其中公钥保存在AirTag中，同时还有一个共享秘钥，用来轮换公钥。

2. Every 2 seconds, the AirTag sends a Bluetooth Low Energy broadcast with the public key as content (changes every 15 minute deterministically using the previously shared secret)

AirTag每隔2秒发出一次BLE广播（报文中包含了公钥），公钥每隔15min会轮换一次，计算需要共享秘钥的参与。

3. Nearby iPhones, Macbooks, etc. recognize the Find My broadcast, retrieve their current location, encrypt the location with the broadcasted public key (using [ECIES](https://iacr.org/archive/pkc2003/25670211/25670211.pdf)) and upload the encrypted location report

Nearby Devices在识别到Find My的BLE广播后，获取自身的位置信息，只有广播中的公钥进行ECIES加密，然后上传密文报告。

4. During device search, the paired Owner Device generates the list of the rolling public keys that the AirTag would have used in the last days and queries an Apple service for their SHA256 hashes. The Apple backend returns the encrypted location reports for the requested key ids

Owner Device生成轮换公钥的列表，预期AirTag也会使用这些，查询时会将公钥的SHA256 hashes告诉Apple Server并进行比对，Apple Server将根据这些hashes值返回位置报告的密文

5. The Owner Device decrypts the location reports and shows an approximate location



<img src="send_my_2_overview.png">



This quite elegant design comes with a few security properties, including:

* Tracking protection against nearby adversaries via rolling public keys

通过轮流公钥保护附近广播设备的行踪（类似BLE广播地址的随机化）

* No access for Apple to user locations

无法向Apple读取用户位置

However, most interestingly for us, Apple does not know which public keys belong to your AirTag, and therefore which location reports were intended for you. This means the endpoint to request location reports for a specific key id does not perform any authorization (but you need to be authenticated with any Apple ID to access the endpoint).

Apple也无法知道哪个公钥属于你的AirTag，因此也不知道哪份位置报告属于你。这意味着终端请求位置报告时不会对Key Id有任何认证，前提是经过Apple Id的登录认证。

The security solely lies in the encryption of the location reports: The location can only be decrypted with the correct private key, which is infeasible to brute force and only stored on the paired Owner Device.

安全性仅存在于位置报告的机密性，因为位置报告只会被正确的私钥（仅保存在本地，不会被暴击攻击）解密。

## Designing a data exfiltration protocol

From this it seems that the only field that we can use to encode data is the broadcasted EC public key (e.g. we can't influence the GPS coordinates as those are added by the Finding device).

唯一能被用作加密秘钥的就是BLE广播中的EC公钥。

For the next section, let's treat the Apple backend as a shared, public key-value store with SHA256 hashes as key, and encrypted location reports as value, with basic operations:

 Apple Server以Key-Value的形式保存位置报告，公钥的SHA256作为Key，位置报告的密文作为Value。且提供基本操作：

* We can probe whether location reports for a specific SHA256 hash exist or not

Probe某个特定SHA256为Key的Value是否存在

* We can add location reports to a specific SHA256 hash by broadcasting the corresponding public key

根据特定SHA256值添加位置报告

I guess you can already see where this is going: We can set arbitrary bits in the shared key-value store and query them again. If both the sender and receiver agree on an encoding scheme, we can transfer arbitrary data.

显然，我们完全可以Set Arbitrary Bits到Apple Server中，再去访问数据。如果Sender与Reciever商量好编码Scheme，我们可以利用Find My Network来传输Arbitrary Data。

I set out to build a modem that takes a message via the serial interface and then sends out this data in a loop until a new message is received. To ensure we can differentiate a "0"-bit from an unset bit, we will broadcast a different public key depending on the bit value and will query both possible public keys on the receiving side.

我开始打造一款Modem，她可以通过串口接收消息，并通过BLE广播循环发送该数据直到有新的消息过来。确保能够区分开**0-bit**跟**为设置bit**，Sender会广播不同的公钥，Reciever会同时查询多个公钥。（为什么这样做会区分开0-bit跟unset-bit？为什么要区分开？）

There is no guarantee as to when or whether at all specific broadcasts are uploaded to the Apple backend as location reports. This is because some packets might not reach any Apple device and the Finding devices can have highly variable delays between receiving a broadcast and uploading the location report, e.g. depending on their upstream connectivity or power mode. This means our data encoding must be independent of the ordering in which location reports are received, and able to recover partial data streams when some bits are missing entirely. To achieve this, I decided to encode a single bit of data per broadcast together with an index value indicating which bit of the message is being set. Additional message and modem IDs allow the system to be reused for multiple messages and by multiple users.

没有机制保证指定BLE广播在什么时间会上传位置报告，也不保证是否会上传。因为**丢失配件**的广播不一定能找到Finding Device，况且Finding Device可能从接收到BLE广播到上传位置报告之间存在长延时，这取决于Finding Device的连接情况或电源模式。这意味着加密数据能够独立解密，不依赖于接收端对数据的接收顺序，并且在有bits数据丢失时，能够恢复部分的数据流。为了实现她，对消息加上序列号并且进行加密。额外的信息和Modem ID使得系统可支持多用户多消息。

So when sending a specific bit, we create a 28-byte array of the form "[4b bit index] [4b message ID] [4b modem ID] [padding 0s...] [bit value]", treat this as the public key and send BLE advertisements to e.g. broadcast the information "bit 0 of message 0 is 1".

我们创建特定格式的数据（28bytes）发送到Find My Network，并且将她视作公钥。

| octet    | size   | description |
| -------- | ------ | ----------- |
| [0, 4)   | 4bytes | bit index   |
| [4, 8)   | 4bytes | message ID  |
| [8, 12)  | 4bytes | modem ID    |
| [12, 23) | 7bytes | Padding 0s  |
| 23       | 1byte  | bit value   |



To send a full message, the program simply loops over its bits and sends out one advertisement per bit with the public key that encodes its index and value.

应该如何发送完整的消息呢，程式通过简单轮询消息的bits，并且针对每个bit都发出一条BLE广播，并且用公钥加密消息的序列号跟值。

<img src="send_my_3_encoding.png">



When fetching data, the receiving application will generate the same 28-byte arrays (two per bit, for the possible bit values 0 and 1) and query the Apple service with the SHA256 hashes of those "public keys". Only one of the two key ids should have location reports attached, which can then be interpreted (e.g. bit at index 0 equals 1).

拉取数据时，接收端会生成同样大小的28byte数组，然后传入SHA256向Apple Server来query数据。两个Key中只有一个能够匹配到位置报告，然后位置报告可被解密与解析。

<img src="send_my_4_retrieving.png">



Note: Instead of only transferring one bit per message, we could also e.g. send a full byte by setting the last 8 bit of the public key. While this increases the sending bandwidth, on the receiving side, we now need to request 255 different key ids to fetch/"brute force" one byte (compared to 16 key IDs when it's encoded bit-by-bit).

如果用8bits来变化作为公钥的话，接收端则会生成255种不同的Key去query Apple Server，除了增加带宽外，Owner Device还需要根据255种秘钥进行暴力解密。

## Implementation



### Sending side

“丢失设备”端

For the sending side I chose the ESP32, as it is a very common and low-cost microcontroller (and in a quick test it could change its BT MAC address much more quickly than e.g. a Linux-based Raspberry Pi). On boot, the OpenHaystack-based firmware broadcasts a hardcoded default message and then listens on the serial interface for any new data to broadcast in a loop until a new message is received. Broadcasting the public key actually means splitting it up and encoding the first 6 bytes in the Bluetooth MAC address (except for the first two bits as the Bluetooth standard requires them to be set to 1). You can check [Section 6.2 in the TU Darmstadt paper](https://arxiv.org/pdf/2103.02282.pdf) for more details on this hacky encoding.

使用ESP32作为“丢失设备”。启动时，OpenHaystack会监听串口的消息，同时进行默认消息的循环BLE广播，直到收到新的串口消息。广播公钥实际上意味着拆分她且编码前6bytes大小的蓝牙MAC地址。（没看懂这句是什么意思？）

I added a static prefix to my payload to not run into issues with the BT specification, and also included the incrementing bit index in the first 6 bytes of the public key, resulting in a different BT MAC address used for each transmitted bit, just in case there is some MAC address based rate limiting somewhere in the stack.

我会为消息添加特殊的前缀避免遇到蓝牙规范问题，同时也包含递增的序列号在公钥的前6个bytes中。导致每个transmitted bit使用不同的蓝牙MAC地址，那么消息传输速率的限制就在于MAC地址的变化速度。（ESP32比树莓派的蓝牙MAC地址切换得快，是ESP32的优势。但是，好奇怪的数据传输设计，不知所云）

### Retrieval side

“接收”端，Owner Device

The Mac application is also based on OpenHaystack and uses the same AppleMail plugin trick to send properly authenticated location retrieval requests to the Apple backend. The user is prompted for the 4 byte modem ID (can be set when flashing the ESP firmware), after which the application will automatically fetch, decode and display the message with id 0. Afterwards the user can fetch other messages or change the modem.

MacOS application也是基于OpenHaystack的程式，利用AppleMail plugin欺骗Apple Server，发送合法的位置报告的请求。

A message is fetched 16 bytes (128 bit) at a time (by querying 256 key ids) until no reports can be found (for a full byte).



### Small complication: public key validity

有效公钥的计算（最硬核部分）

Having implemented both the sending and receiving side, I performed a first test by broadcasting and trying to receive a 32 bit value. After a few minutes, I could retrieve 23 out of the 32 bits, each one being unambiguous and with ~100 location reports, but no reports for the remaining 9 bits.

发送端与接收端都已经实现后，我执行第一次测试，广播32bit（4bytes）的内容，并向Apple Server请求。几分钟后，我获取到32bits中的23个bit，且每个消息都是正确的，大约有100多份位置报告，但没有一份报告有关于剩余9bits的消息。

I suspected that some of the generated public keys were rejected by the nearby Apple Devices during the ECIES encryption as invalid public keys, and could quickly confirm this by trying to import each of the generated payloads as SEC1-encoded public keys on the P224 curve using Python's fastecdsa: For every bit that I could not find location reports for, the microcontroller had broadcasted a public key, which throws an InvalidSEC1PublicKey exception during the fastecdsa key import.

我怀疑生成的公钥中有一些是无效的，在Finding Device执行ECIES加密时会发生错误。我用Python‘s fastecdsa包取得了快速验证。

Some background info on the crypto involved:

密码技术涉及如下一些背景知识

- The 28-byte EC public represents the SEC1-encoded X coordinate of a point

28byte大小的EC公钥表示SEC1编码坐标系中一个点的X坐标。（没看懂什么意思）

- A SEC1 public key usually also has a "sign" bit that defines which of the two possible Y coordinates for a specific X coordinate should be encoded. This bit is not broadcasted and irrelevant for the public key's validity

SEC1 公钥通常还有一个“符号”位，用于定义应编码特定 X 坐标的两个可能 Y 坐标中的哪一个。 该位不广播，与公钥的有效性无关。

- During the decoding of a compressed public key, the corresponding Y coordinate is calculated using the fixed curve parameters and tested for validity. This is the test that fails for some of the generated public keys. You can check Section 3.2.2 of "[Validation of Elliptic Curve Public Keys](https://iacr.org/archive/pkc2003/25670211/25670211.pdf)" for more details:

在压缩公钥的解码过程中，使用固定曲线参数计算相应的 Y 坐标并测试其有效性。 这是某些生成的公钥失败的测试。

<img src="send_my_5_ecc_validity.png">



There are at least two ways to solve this problem of invalid public keys:

1. Before broadcasting a payload, check whether the EC point it represents is actually valid for the used curve. If not, increment a counter until a valid public key is found. This process is deterministic and can similarly be performed offline by the retrieval application before querying a key id

在广播有效载荷之前，请检查它所代表的 EC 点对于所使用的曲线是否确实有效。 如果没有，增加一个计数器直到找到一个有效的公钥。 此过程是确定性的，并且可以类似地由检索应用程序在查询密钥 ID 之前离线执行。

2. Interpret the payload as private key (instead of public key). While a compressed 28 byte public key is interpreted as the X coordinate of a potential point on the curve, a 28 byte private key is interpreted as the scalar in a [EC point/scalar multiplication](https://en.wikipedia.org/wiki/Elliptic_curve_point_multiplication), thus always resulting in a valid point on the curve (the public key)

将有效负载解释为私钥（而不是公钥）。 压缩的 28 字节公钥被解释为曲线上潜在点的 X 坐标，而 28 字节私钥被解释为 [EC 点/标量乘法](https://en.wikipedia.org) 中的标量 /wiki/Elliptic_curve_point_multiplication），因此总是在曲线上产生一个有效点（公钥）

The second option has the advantage that for each received bit, we'd also be able to decrypt the location reports to find out the location it was received at, but it requires a bit more processing. While implementing this option, I found that due to [bugs in the EC multiplication implementation](https://github.com/kmackay/micro-ecc/issues/128) of the used uECC library, for some private keys the ESP would calculate different public keys than both BoringSSL on Mac and Python's fastecdsa (accidential differential fuzzing?). Those public keys were even treated as invalid by uECC's own uECC_valid_public_key() function. I therefore chose to go with option 1 for this PoC.

第二个选项的优点是，对于每个接收到的位，我们还可以解密位置报告以找出接收它的位置，但它需要更多的处理。但uECC库有bug导致生成的公钥不合法，所以最终采用第一个选项。

<img src="send_my_6_sending.png">



## Testing / Performance



With the public key validity check implemented, everything worked flawlessly. While I didn't do extensive performance testing and measurements, here are some estimates:

虽然我没有进行大量的性能测试和测量，但这里有一些估计：

- The **sending rate** on the microcontroller is currently **~3 bytes/second**. Higher speeds could be achieved e.g. simply by caching the encoding results or by encoding one byte per advertisement

微控制器上的**发送速率**目前为 **~3 字节/秒**。可以实现更高的速度，例如只需缓存编码结果或对每个广播编码一个字节。

- In my tests, the **receiving rate** was limited by slow Mac hardware. Retrieving **16 bytes** within one request takes **~5 seconds**

在我的测试中，**接收率**受到慢速 Mac 硬件的限制。 在一个请求中检索 **16 字节** 需要 **~5 秒**。

- The **latency** is usually **between 1 and 60 minutes** depending on how many devices are around and other random factors. The following graphic shows the delay distribution between a public key broadcast and the corresponding location report being uploaded. Please note however, that this is per location report upload and does not directly represent the time until broadcasted data can be downloaded (already the first location report from any nearby Apple devices suffices for this)

**延迟**通常**介于 1 到 60 分钟** 之间，具体取决于周围的设备数量和其他随机因素。



<img src="send_my_7_report_delays.png">





## Potential use cases



While I was mostly just curious about whether it would be possible, I would imagine the most common use case to be **uploading sensor readings or any data** from **IoT devices** without a broadband modem, SIM card, data plan or Wifi connectivity. With Amazon [running a similar network called *Sidewalk* that uses Echo devices](https://www.amazon.com/Amazon-Sidewalk/b?ie=UTF8&node=21328123011) there might very well be demand for it. Since the Finding devices cache received broadcasts until they have an Internet connection, the sensors can even send out data from areas without mobile coverage as long as people pass the area.



In the world of **high-security networks**, where combining lasers and scanners seems to be a [noteworthy technique](https://www.schneier.com/blog/archives/2017/04/jumping_airgaps.html) to bridge the airgap, the visitor's Apple devices might also become feasible intermediaries to **exfiltrate data from** certain **airgapped systems** or Faraday caged rooms.



It also seems like the Offline Finding protocol could be used to **deplete nearby iPhone's mobile data plans**. With the number of location reports from a Finder device being limited (to 255 reports/submission due to a 1 byte count value) and each report being over 100 byte, broadcasting many unique public keys should result in an amplified amount of mobile traffic sent by the phone. While I haven't noticed any rate limiting on the number of location reports sent out, I also haven't tested how much data this would consume.



## Mitigation

减轻滥用的情况

As mentioned initially, it would be hard for Apple to defend against this kind of misuse in case they wanted to.

正如最初提到的那样，如果 Apple 愿意的话，很难防范这种滥用。

Apple designed the system on the principle of data economy. They cannot read unencrypted locations and do not know which public keys belong to your AirTag, or even which public key a certain encrypted location report belongs to (as they only receive the public key's SHA256 hash).

苹果根据数据经济的原则设计了该系统。 他们无法读取未加密的位置，也不知道哪些公钥属于您的 AirTag，甚至不知道某个加密位置报告属于哪个公钥（因为他们只接收公钥的 SHA256 哈希值）。

In this light, the stated restriction of 16 AirTags per Apple ID seems interesting, as to me it does not seem that Apple can currently enforce this.

有鉴于此，声明的每个 Apple ID 16 个 AirTag 的限制似乎很有趣，在我看来，Apple 目前似乎无法强制执行此操作。

However, further hardening of the system might e.g. be possible in the following two areas:

然而，系统的进一步强化可能例如 可以在以下两个方面进行：

- **Authentication of the BLE advertisement.** Currently, Finder devices can not differentiate between e.g. an AirTag and a clone based on OpenHaystack, thus allowing the spoofing of many thousand non-existing AirTags to encode and transmit data. Usually one would consider signing the public keys, however with the BLE advertisement size already completely used up, AirTags being low power and not connected to the internet, and the broadcasted keys constantly rotating, this presents quite a challenge.

**BLE 广播的认证。** 目前，Finder 设备无法区分例如 一个 AirTag 和一个基于 OpenHaystack 的克隆，从而允许欺骗数千个不存在的 AirTag 来编码和传输数据。 通常会考虑对公钥进行签名，但是由于 BLE 广播大小已经完全用完，AirTags 低功耗且未连接到互联网，并且广播的密钥不断轮换，这提出了相当大的挑战。

- **Rate limiting of the location report retrieval.** While Apple does not know whether the requested key id belongs to one of the requesting user's AirTag, they could cache the requested key ids and ensure that only 16 new key ids are queried per 15 minutes and Apple ID (after allowing a much higher number for an initial search during the last days). While easier to implement, this mitigation can be bypassed by cycling through multiple free Apple IDs for data retrieval.

**位置报告检索的速率限制。** 虽然 Apple 不知道请求的密钥 ID 是否属于请求用户的 AirTag 之一，但他们可以缓存请求的密钥 ID 并确保每 15 个仅查询 16 个新的密钥 ID 分钟和 Apple ID（在最后几天允许更高的初始搜索数字之后）。 虽然更容易实现，但可以通过循环使用多个免费 Apple ID 进行数据检索来绕过这种缓解措施。

## Conclusion



In this blog post, we have answered the initial question, whether it's possible to upload arbitrary data using other people's Apple devices, with a clear yes.

在这篇博文中，我们已经回答了最初的问题，即是否可以使用其他人的 Apple 设备上传任意数据，答案是肯定的。

An ESP32 modem firmware and macOS data retrieval application was implemented and is [available on Github](https://github.com/positive-security/send-my) for others to experiment with.



Please note that this is a PoC implementation and the "protocol" itself is neither encrypted nor authenticated. Exemplary, you can explore the data of modem ID 0x42424242 by simply entering its ID (maybe in the meantime somebody has also demonstrated the protocol's lack of authentication 😉).

请注意，这是一个 PoC 实现，“协议”本身既未加密也未经过身份验证。 例如，您可以通过简单地输入调制解调器 ID 来探索调制解调器 ID 0x42424242 的数据（也许同时有人也证明了该协议缺乏身份验证😉）。

Final note: While writing this blog post, I noticed a "status" byte that is included in the BLE advertisement and apparently used e.g. as battery level indicator. In combination with deterministically generated rotating private keys, this is probably another way to leak data with one byte per advertisement, but I haven't tested this approach.

最后一点：在写这篇博文时，我注意到 BLE 广播中包含一个“状态”字节，显然使用了例如 作为电池电量指示器。 结合确定性生成的旋转私钥，这可能是另一种以每个广告一个字节泄漏数据的方法，但我还没有测试过这种方法。















