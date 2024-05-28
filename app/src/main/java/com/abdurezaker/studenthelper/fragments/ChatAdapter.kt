package com.abdurezaker.studenthelper.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.abdurezaker.studenthelper.R
import com.google.firebase.auth.FirebaseAuth


class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatHolder>() {
    private val MESSAGE_SENT=1
    private val MESSAGE_RECEIVED=2
    class ChatHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
    //farklılık olup olmadığını gösterir
    private val diffUtil= object : DiffUtil.ItemCallback<Chat>(){
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
           return oldItem==newItem
        }

       // @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem==newItem

        }

    }
    private val recyclerDiffer=AsyncListDiffer(this,diffUtil)
        

    var chats:List<Chat>
        get()=recyclerDiffer.currentList
            set(value)=recyclerDiffer.submitList(value)

    override fun getItemViewType(position: Int): Int {

        val chat=chats.get(position)
        if(chat.user==FirebaseAuth.getInstance().currentUser?.email.toString()){
            return MESSAGE_SENT
        }else{
            return MESSAGE_RECEIVED
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        if(viewType==MESSAGE_RECEIVED){
            val view =LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
            return ChatHolder(view)
        }else{
            val view =LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_right,parent,false)
            return ChatHolder(view)
        }

    }

    override fun getItemCount(): Int {
            return chats.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val textView=holder.itemView.findViewById<TextView>(R.id.recyclerChatView)
        textView.text="${chats.get(position).user} : ${chats.get(position).text}"
    }
}






