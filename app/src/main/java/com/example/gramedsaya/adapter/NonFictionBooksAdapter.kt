package com.example.gramedsaya.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gramedsaya.R
import com.example.gramedsaya.ui.list.DetailActivity

class NonFictionBooksAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val nonfictionBooksImage = context.resources.obtainTypedArray(R.array.nonfiction_books_image)
    private val nonfictionBooksName = context.resources.getStringArray(R.array.nonfiction_books_title)
    private val nonfictionBooksDesc = context.resources.getStringArray(R.array.nonfiction_books_desc)
    private val nonfictionBooksSynopsis = context.resources.getStringArray(R.array.nonfiction_books_synopsis)

    private var isGridMode = false

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val booksImage: ImageView = itemView.findViewById(R.id.list_image_arti)
        val booksName: TextView = itemView.findViewById(R.id.list_arti_name)
        val booksDesc: TextView = itemView.findViewById(R.id.list_arti_description)
        val booksStatus: TextView = itemView.findViewById(R.id.list_arti_status)
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val booksImage: ImageView = itemView.findViewById(R.id.grid_image_arti)
        val booksName: TextView = itemView.findViewById(R.id.grid_arti_name)
        val booksDesc: TextView = itemView.findViewById(R.id.grid_arti_description)
        val booksStatus: TextView = itemView.findViewById(R.id.grid_arti_status)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) FictionBooksAdapter.GRID_VIEW_TYPE else FictionBooksAdapter.LIST_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = if (viewType == FictionBooksAdapter.GRID_VIEW_TYPE) R.layout.item_books_grid else R.layout.item_books_list
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return if (viewType == FictionBooksAdapter.GRID_VIEW_TYPE) GridViewHolder(view) else ListViewHolder(view)
    }

    override fun getItemCount(): Int { return nonfictionBooksName.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val status = SharedPreferencesHelper.getStatus(context, nonfictionBooksName[position])

        if (holder.itemViewType == FictionBooksAdapter.GRID_VIEW_TYPE) {
            val gridHolder = holder as GridViewHolder
            gridHolder.booksImage.setImageResource(nonfictionBooksImage.getResourceId(position, -1))
            gridHolder.booksName.text = nonfictionBooksName[position]
            gridHolder.booksDesc.text = nonfictionBooksDesc[position]
            gridHolder.booksStatus.text = status
            gridHolder.itemView.setOnClickListener {
                context.startActivity(
                    DetailActivity.newIntent(
                        context,
                        nonfictionBooksImage.getResourceId(position, -1),
                        nonfictionBooksName[position],
                        nonfictionBooksDesc[position],
                        nonfictionBooksSynopsis[position]
                    )
                )
            }
        } else {
            val listHolder = holder as ListViewHolder
            listHolder.booksImage.setImageResource(nonfictionBooksImage.getResourceId(position, -1))
            listHolder.booksName.text = nonfictionBooksName[position]
            listHolder.booksDesc.text = nonfictionBooksDesc[position]
            listHolder.booksStatus.text = status
            listHolder.itemView.setOnClickListener {
                context.startActivity(
                    DetailActivity.newIntent(
                        context,
                        nonfictionBooksImage.getResourceId(position, -1),
                        nonfictionBooksName[position],
                        nonfictionBooksDesc[position],
                        nonfictionBooksSynopsis[position]
                    )
                )
            }
        }
    }

    fun setGridMode(isGrid: Boolean) {
        isGridMode = isGrid
        notifyDataSetChanged()
    }
}