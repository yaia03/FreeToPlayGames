package space.quiz.freetoplaygames.UI.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.databinding.GameItemBinding

class GamesAdapter(private var gameList: List<Game>, private val onClickListener: GameOnClickListener,
                    val context: Context): RecyclerView.Adapter<GamesAdapter.GamesHolder>(){

    inner class GamesHolder(val binding: GameItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val mBinding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesHolder(mBinding)
    }

    override fun onBindViewHolder(holder: GamesHolder, position: Int) {
        val game = gameList[position]
        holder.binding.gameItemName.text = game.title
        val uri = Uri.parse(game.thumbnail)
        Glide
            .with(context)
            .load(uri)
            .into(holder.binding.gameItemImage)

        holder.itemView.setOnClickListener {
            onClickListener.onClicked(game)
        }
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun filterList(list: List<Game>){
        gameList = list
        notifyDataSetChanged()
    }
}