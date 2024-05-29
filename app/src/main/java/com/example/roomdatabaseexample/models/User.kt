package com.example.roomdatabaseexample.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class User:Serializable {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0

    var name:String? = null
    var nummber:String? = null

    constructor(name: String?, nummber: String?) {
        this.name = name
        this.nummber = nummber
    }

    override fun toString(): String {
        return "User(id=$id, name=$name, nummber=$nummber)"
    }

}