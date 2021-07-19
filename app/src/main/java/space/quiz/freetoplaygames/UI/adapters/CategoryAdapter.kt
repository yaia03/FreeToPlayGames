package space.quiz.freetoplaygames.UI.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.databinding.CategoryItemBinding

class CategoryAdapter(private var gameList: List<Game>, private val onClickListener: GameOnClickListener,
                      val context: Context): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>(){

    inner class CategoryHolder(val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val mBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(mBinding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val game = gameList[position]
        holder.binding.categoryItemName.text = game.title
        holder.binding.categoryItemDescription.text = game.short_description
        val uri = Uri.parse(game.thumbnail)
        Glide
            .with(context)
            .load(uri)
            .into(holder.binding.categoryItemImage)

        holder.itemView.setOnClickListener {
            onClickListener.onClicked(game)
        }
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun filterList(filterList: List<Game>){
        gameList = filterList
        notifyDataSetChanged()
    }

}