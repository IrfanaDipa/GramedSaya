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

class FictionBooksAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val LIST_VIEW_TYPE = 0
        const val GRID_VIEW_TYPE = 1
    }

    private val fictionBooksImage = context.resources.obtainTypedArray(R.array.fiction_books_image)
    private val fictionBooksName = context.resources.getStringArray(R.array.fiction_books_title)
    private val fictionBooksDesc = context.resources.getStringArray(R.array.fiction_books_desc)
    private val fictionBooksSynopsis = context.resources.getStringArray(R.array.fiction_books_synopsis)

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
        return if (isGridMode) GRID_VIEW_TYPE else LIST_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = if (viewType == GRID_VIEW_TYPE) R.layout.item_books_grid else R.layout.item_books_list
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return if (viewType == GRID_VIEW_TYPE) GridViewHolder(view) else ListViewHolder(view)
    }

    override fun getItemCount(): Int { return fictionBooksName.size }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val status = SharedPreferencesHelper.getStatus(context, fictionBooksName[position])

        if (holder.itemViewType == GRID_VIEW_TYPE) {
            val gridHolder = holder as GridViewHolder
            gridHolder.booksImage.setImageResource(fictionBooksImage.getResourceId(position, -1))
            gridHolder.booksName.text = fictionBooksName[position]
            gridHolder.booksDesc.text = fictionBooksDesc[position]
            gridHolder.booksStatus.text = status
            gridHolder.itemView.setOnClickListener {
                context.startActivity(
                    DetailActivity.newIntent(
                        context,
                        fictionBooksImage.getResourceId(position, -1),
                        fictionBooksName[position],
                        fictionBooksDesc[position],
                        fictionBooksSynopsis[position]
                    )
                )
            }
        } else {
            val listHolder = holder as ListViewHolder
            listHolder.booksImage.setImageResource(fictionBooksImage.getResourceId(position, -1))
            listHolder.booksName.text = fictionBooksName[position]
            listHolder.booksDesc.text = fictionBooksDesc[position]
            listHolder.booksStatus.text = status
            listHolder.itemView.setOnClickListener {
                context.startActivity(
                    DetailActivity.newIntent(
                        context,
                        fictionBooksImage.getResourceId(position, -1),
                        fictionBooksName[position],
                        fictionBooksDesc[position],
                        fictionBooksSynopsis[position]
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