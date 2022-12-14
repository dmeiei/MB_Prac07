package kr.ac.hallym.prac07_exer

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.prac07_exer.databinding.ActivityMainBinding
import kr.ac.hallym.prac07_exer.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contents = mutableListOf<String>("Mobile Programing", "Computer Graphics", "Game Programming")
        val contents_sub = mutableListOf<String>("2022년 2학기", "2022년 1학기", "2022년 2학기")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(contents, contents_sub)
        binding.recyclerView.addItemDecoration(MyDecoration(this))
    }

    class MyViewHolder(val binding: ItemRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val contents: MutableList<String>,val contents_sub: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            Log.d("kkang","init contents size: ${contents.size}")
            return contents.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
        = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            Log.d("kkang","onBindViewHolder: $position")
            val binding = (holder as MyViewHolder).binding
            //뷰에 데이터 출력
            binding.itemData.text = contents[position]
            binding.itemSubdata.text = contents_sub[position]
            //뷰에 이벤트 추가
            binding.itemRoot.setOnClickListener {
                Log.d("kkang", "item root click: $position")
            }
        }
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
        }
//        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//            super.onDrawOver(c, parent, state)
//            //뷰 크기 계산
//            val width = parent.width
//            val height = parent.height
//            //이미지 크기 계산
//            val img: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_launcher_background, null)
//            val imgWidth = img?.intrinsicWidth
//            val imgHeight = img?.intrinsicHeight
//            //이미지가 그려질 위치 계산
//            val left = width / 2 - imgWidth?.div(2) as Int
//            val top = height / 2 - imgHeight?.div(2) as Int
////            c.drawBitmap(
////                BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background),
////                left.toFloat(), top.toFloat(), null)
//        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) +1
            if (index % 3 == 0)
                outRect.set(30, 20, 20, 60)
            else
                outRect.set(30, 20, 20, 10)
            view.setBackgroundColor(Color.LTGRAY)
            ViewCompat.setElevation(view, 20.0f)
        }
    }
}