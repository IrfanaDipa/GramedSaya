package com.example.gramedsaya.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gramedsaya.R
import com.example.gramedsaya.adapter.FictionBooksAdapter
import com.example.gramedsaya.adapter.NonFictionBooksAdapter

class ListFragment : Fragment() {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var fictionAdapter: FictionBooksAdapter
    private lateinit var nonFictionAdapter: NonFictionBooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

//        For recommended book section
        val recommendBooksDetail: CardView = view.findViewById(R.id.list_recommended)
        val recommendBooksImage: ImageView = view.findViewById(R.id.list_recommend_image)
        val recommendBooksName: TextView = view.findViewById(R.id.list_recommend_name)
        val recommendBooksDesc: TextView = view.findViewById(R.id.list_recommend_desc)

        val fictionImageArray = resources.obtainTypedArray(R.array.fiction_books_image)
        val nonfictionImageArray = resources.obtainTypedArray(R.array.nonfiction_books_image)
        val fictionName = resources.getStringArray(R.array.fiction_books_title)
        val nonfictionName = resources.getStringArray(R.array.nonfiction_books_title)
        val fictionDesc = resources.getStringArray(R.array.fiction_books_desc)
        val nonfictionDesc = resources.getStringArray(R.array.nonfiction_books_desc)
        val fictionSynopsis = resources.getStringArray(R.array.fiction_books_synopsis)
        val nonfictionSynopsis = resources.getStringArray(R.array.nonfiction_books_synopsis)

        val fictionImage = IntArray(fictionImageArray.length()) { fictionImageArray.getResourceId(it, 0) }
        val nonfictionImage = IntArray(nonfictionImageArray.length()) { nonfictionImageArray.getResourceId(it, 0) }

        val image = fictionImage + nonfictionImage
        val name = fictionName + nonfictionName
        val desc = fictionDesc + nonfictionDesc
        val synopsis = fictionSynopsis + nonfictionSynopsis

        val randomIndex = (image.indices).random()

        recommendBooksDetail.setOnClickListener {
            context?.startActivity(
                DetailActivity.newIntent(
                    requireContext(),
                    image[randomIndex],
                    name[randomIndex],
                    desc[randomIndex],
                    synopsis[randomIndex]
                )
            )
        }
        recommendBooksImage.setImageResource(image[randomIndex])
        recommendBooksName.text = name[randomIndex]
        recommendBooksDesc.text = desc[randomIndex]

        fictionImageArray.recycle()
        nonfictionImageArray.recycle()

//        For coming soon book section
        val comingsoonDetail: CardView = view.findViewById(R.id.list_comingsoon)
        val comingsoonBookImage: ImageView = view.findViewById(R.id.list_comingsoon_image)
        val comingsoonBookName: TextView = view.findViewById(R.id.list_comingsoon_name)
        val comingsoonBookDesc: TextView = view.findViewById(R.id.list_comingsoon_desc)

        val comingsoonName = resources.getString(R.string.comingsoon_book_title)
        val comingsoonDesc = resources.getString(R.string.comingsoon_books_desc)
        val comingsoonBookSynopsis = resources.getString(R.string.comingsoon_book_synopsis)

        comingsoonBookImage.setImageResource(R.drawable.reckless)
        comingsoonDetail.setOnClickListener {
            context?.startActivity(
                DetailActivity.newIntent(
                    requireContext(),
                    R.drawable.reckless,
                    comingsoonName,
                    comingsoonDesc,
                    comingsoonBookSynopsis
                )
            )
        }
        comingsoonBookName.text = comingsoonName
        comingsoonBookDesc.text = comingsoonDesc

//        For fiction books section
        recyclerView1 = view.findViewById(R.id.rv_fiction_books)
        recyclerView1.layoutManager = LinearLayoutManager(context)
        fictionAdapter = FictionBooksAdapter(requireContext())
        recyclerView1.adapter = fictionAdapter

//        For nonfiction books section
        recyclerView2 = view.findViewById(R.id.rv_nonfiction_books)
        recyclerView2.layoutManager = LinearLayoutManager(context)
        nonFictionAdapter = NonFictionBooksAdapter(requireContext())
        recyclerView2.adapter = nonFictionAdapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
//                For fiction books section
                recyclerView1.layoutManager = LinearLayoutManager(context)
                fictionAdapter.setGridMode(false)

//                For nonfiction books section
                recyclerView2.layoutManager = LinearLayoutManager(context)
                nonFictionAdapter.setGridMode(false)
                true
            }
            R.id.action_grid -> {
//                For fiction books section
                recyclerView1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                fictionAdapter.setGridMode(true)

//                For nonfiction books section
                recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                nonFictionAdapter.setGridMode(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        // Update data in adapter
        fictionAdapter.notifyDataSetChanged()
        nonFictionAdapter.notifyDataSetChanged()
    }
}