package kr.ac.hallym.prac07_jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.prac07_jetpack.databinding.FragmentOneBinding
import kr.ac.hallym.prac07_jetpack.databinding.ItemRecyclerviewBinding


class OneFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_one, container, false)
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        //리사이클러 뷰를 위한 가상 데이터 준비
        val contents = mutableListOf<String>()
        for (i in 1..9) {
            contents.add("Item $i")
        }
        //리사이클러 뷰에 LayoutManager, Adapter, ItemDecoration 적용
        val adapter = MyAdapter(contents)
        val layoutManager = LinearLayoutManager(activity)
        binding.recycleView.layoutManager = layoutManager
        binding.recycleView.adapter = adapter
        binding.recycleView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }
}

//항목 뷰를 가지는 역할
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

//항목 구성자: 어댑터
class MyAdapter(val contents: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //항목 뷰를 가지는 역할
    override fun getItemCount(): Int {
        return contents.size;
    }
    //항목 구성자: 어댑터
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    //각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text = contents[position]
    }

}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //뷰 크기 계산
        val width = parent.width
        val height = parent.height
        //이미지 크기 계산
        val img: Drawable?= ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val drWith = img?.intrinsicWidth
        val drHeight = img?.intrinsicHeight
        //이미지를 출력할 위치 계산
        val left = width/2 - drWith?.div(2 ) as Int
        val top = height/2 - drHeight?.div(2 ) as Int
        //이미지 출력
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
        left.toFloat(),top.toFloat(),null)
    }

    //각 항목을 꾸미기 위해 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view)+1
        if (index % 3 == 0){
            outRect.set(10,10,10,60)
        }
        else
            outRect.set(10,10,10,0)
        view.setBackgroundColor(Color.parseColor("#28A0FF"))
        ViewCompat.setElevation(view, 20.0f)
    }
}