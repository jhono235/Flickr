package com.example.android.flickrapp.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flickrapp.R
import com.example.android.flickrapp.view.adapter.MainActivityRvAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityContract {

    //number of images displayed per row
    private val spanCount: Int = 3

    private var currentPage : Int = 0
    private var searchedTerm: String = ""
    private var totalNumberOfPages: Int = 0

    private lateinit var  mainActivityPresenter: MainActivityPresenter
    private lateinit var rvPhotoList : RecyclerView
    private lateinit var etPhotoSearch : EditText
    private lateinit var tvCurrentPage : TextView
    private lateinit var ivLeftArrow : ImageView
    private lateinit var ivRightArrow : ImageView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)





        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        initViews()
    }

    private fun initViews(){
        rvPhotoList= findViewById(R.id.rvPhotoList)
        mainActivityPresenter = MainActivityPresenter(this)
        tvCurrentPage = findViewById(R.id.tv_page)
        etPhotoSearch = findViewById(R.id.et_searchTerm)
        ivLeftArrow = findViewById(R.id.iv_left_arrow)
        ivRightArrow = findViewById(R.id.iv_right_arrow)



        etPhotoSearch.setOnEditorActionListener(OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH && etPhotoSearch.text.isNotBlank()) {
                performInitialSearch(1)
                return@OnEditorActionListener true
            }
            Toast.makeText(this, getString(R.string.please_enter_a_search_toast), Toast.LENGTH_SHORT).show()
            false
        })

        ivRightArrow.setOnClickListener(View.OnClickListener { view ->
            searchAdditionalPages(currentPage+1)
        })

        ivLeftArrow.setOnClickListener(View.OnClickListener { view ->
           searchAdditionalPages(currentPage-1)
        })

    }


    override fun onBackPressed() {
        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_favs -> {
             var intent: Intent = Intent(this, FavoritesActivity::class.java)
             startActivity(intent)
            }

        }
        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



    override fun onAdapterReady(adapter: MainActivityRvAdapter?, page: Int, totalPages: String) {
        rvPhotoList.layoutManager = GridLayoutManager(this, spanCount)
        rvPhotoList.adapter = adapter
        currentPage = page
        totalNumberOfPages = totalPages.toInt()

        when {
            totalNumberOfPages == 0 -> {
                lv_arrow_container.visibility = View.INVISIBLE
                Toast.makeText(this, getString(R.string.no_results_toast), Toast.LENGTH_SHORT).show()


            }
            page == 1 -> {
                lv_arrow_container.visibility = View.VISIBLE
                ivLeftArrow.visibility = View.GONE
            }
            else -> {
                ivLeftArrow.visibility = View.VISIBLE
            }
        }
        tvCurrentPage.text = getString(R.string.current_page, page)




    }

   //Null or Blank Search will not send a request.
    private fun performInitialSearch(page: Int){
       val userRequestedTerm: String = etPhotoSearch.text.toString()
       var page = page
       var parsedTotalPages : Int = totalNumberOfPages


       if (userRequestedTerm.isNotBlank()){
           mainActivityPresenter.getPhotos(userRequestedTerm, page)
           searchedTerm = userRequestedTerm
       }

       //Arrows will still perform search if search edittext is blank after initial search
      if (userRequestedTerm.isBlank()
          && searchedTerm.isNotBlank()){
           mainActivityPresenter.getPhotos(searchedTerm, page)
       }


    }

    //this method is used by the arrow UI to view additional pages
    private fun searchAdditionalPages(page: Int) {
        var page = page
        var parsedTotalPages : Int = totalNumberOfPages


        //search will not occur when no more pages are available
        if (searchedTerm.isNotBlank()
            && page <= parsedTotalPages) {
            mainActivityPresenter.getPhotos(searchedTerm, page)
        }

        else
            Toast.makeText(this, getString(R.string.end_of_results_toast), Toast.LENGTH_SHORT).show()
    }




    companion object QueryStrings {
        const val method: String = "flickr.photos.search"
        const val format: String = "json"
        const val media: String = "photos"
        const val noJson_Callback: String = "1"
        const val perPage: String = "25"

    }
}
