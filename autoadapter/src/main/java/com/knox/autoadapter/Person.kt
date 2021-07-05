package com.knox.autoadapter

import com.knox.autoadapter_annotations.AdapterModel
import com.knox.autoadapter_annotations.ViewHolderBinding

@AdapterModel(R.layout.layout_person)
data class Person(
    @ViewHolderBinding(R.id.person_name) var name: String,
    @ViewHolderBinding(R.id.person_address) var address: String
)
