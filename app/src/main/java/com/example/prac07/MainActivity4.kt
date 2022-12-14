package com.example.prac07

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.prac07.databinding.ActivityMain4Binding
import com.example.prac07.databinding.ItemPagerBinding

class MainActivity4 : AppCompatActivity() {
    val binding4 by lazy {
        ActivityMain4Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main4)
        setContentView(binding4.root)

        /*val contents = mutableListOf<String>()
        for(i in 1..3){
            contents.add("item $i")
        }*/
//        binding4.viewPager.adapter = MyPagerAdapter(contents)
        binding4.viewPager.adapter = MyFragmentPagerViewAdapter(this)
    }
}

class MyFragmentPagerViewAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
    val fragments: List<Fragment>
    init {
        fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        Log.d("kkang", "fragments size: ${fragments.size}")
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}

/*class MyPagerViewHolder(val binding: ItemPagerBinding): RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val contents: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    =MyPagerViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding
        //뷰에 데이터 출력
        binding.itemPagerTextView.text = contents[position]
        when(position % 3){
            0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
            1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
            2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
        }
    }
}*/