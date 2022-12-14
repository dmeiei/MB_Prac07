package kr.ac.hallym.prac07_jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.ac.hallym.prac07_jetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    //뷰 페이저 어댑터
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments:List<Fragment>
        init{
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun getItemCount(): Int  = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
        R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        //뷰 페이저에 어댑처 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)

        //MenuItem 객체를 얻고 그 안에 포함된 ActionView 객체 획득
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("kkang", "search text: $query")
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 토글 버튼에서 발생하면
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}