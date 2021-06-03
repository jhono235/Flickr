package com.example.android.flickrapp.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.android.flickrapp.R
import com.example.android.flickrapp.databinding.ActivityFullSizeImage2Binding
import com.example.android.flickrapp.model.StringConstants
import com.example.android.flickrapp.model.room.FavoriteDatabase
import com.example.android.flickrapp.model.room.FavoriteList


class FullSizeImageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFullSizeImage2Binding



    var id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullSizeImage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_full_size_image2)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val url: String? = intent.getStringExtra(StringConstants.passedUrl)
        val title: String? = intent.getStringExtra(StringConstants.passedTitle)
        id = intent.getStringExtra(StringConstants.passedId)




        Log.i("tag", "fromActivity URL " + url)
        Log.i("tag", "fromActivity Title " + title)
        Log.i("tag", "fromActivity id" + id)


        sendDataToFullScreenFrag()

        favoriteDatabase = Room.databaseBuilder(
            applicationContext,
            FavoriteDatabase::class.java,
            StringConstants.databaseName
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_full_size_image2)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun sendDataToFullScreenFrag() {
        //PACK DATA IN A BUNDLE
        val bundle = Bundle()
        bundle.putString(StringConstants.passedUrl, intent.getStringExtra("passedURL"))

        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        val myFragment = FullSizeImageFragment()
        myFragment.arguments = bundle


        //THEN NOW SHOW OUR FRAGMENT
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_full_size_image2, myFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.favorites_menu, menu)
        return true
    }

    //Changes the menu item depending on if the favorite already exists in database
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)

        if (favoriteDatabase.favoriteDao().isFavorite(id) == 1.toString()) {
            menu.findItem(R.id.add_to_favs).title = getString(R.string.remove_favorite)
        }



        return true
    }

    //stores user selected favorites to  Room Database
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val favoriteList = FavoriteList()
        val url: String? = intent.getStringExtra(StringConstants.passedUrl)
        val title: String? = intent.getStringExtra(StringConstants.passedTitle)
        val id: String? = intent.getStringExtra(StringConstants.passedId)

        if (!id.isNullOrBlank()) {
            favoriteList.id = id
        }

        if (!url.isNullOrBlank()) {
            favoriteList.url = url
        }

        if (!title.isNullOrBlank()) {
            favoriteList.title = title
        }

        if (!id.isNullOrBlank()) {
            if (favoriteDatabase.favoriteDao().isFavorite(id) != 1.toString()) {
                item.title = getString(R.string.remove_favorite)
                favoriteDatabase.favoriteDao().addData(favoriteList)
                Toast.makeText(
                    this,
                    getString(R.string.favorite_added_toast),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                item.title = getString(R.string.add_favorite)
                favoriteDatabase.favoriteDao().delete(favoriteList)
                Toast.makeText(
                    this,
                    getString(R.string.favorite_removed_toast),
                    Toast.LENGTH_SHORT
                ).show()

            }

            return true
        }
        return false
    }

    companion object {
        lateinit var favoriteDatabase: FavoriteDatabase
    }
}








