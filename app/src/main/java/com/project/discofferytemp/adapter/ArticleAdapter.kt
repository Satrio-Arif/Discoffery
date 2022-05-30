package com.project.discofferytemp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.discofferytemp.DetailKopiArticle
import com.project.discofferytemp.databinding.UtemArticleBinding
import com.project.discofferytemp.model.Articles

class ArticleAdapter(val context: Context):RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    private var dataArticle =ArrayList<Articles>()

    fun setData(param:ArrayList<Articles>){
         this.dataArticle.clear()
         this.dataArticle.addAll(param)
         notifyDataSetChanged()
     }


    class ArticleHolder(var data:UtemArticleBinding):RecyclerView.ViewHolder(data.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleHolder {
        val view = UtemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.data.imgItemPhoto.setImageResource(dataArticle[position].img)
        holder.data.tvJudulArtikel.text =dataArticle[position].judul
        holder.data.tvItemSource.text =dataArticle[position].sumber
        holder.data.tvItemCreatedAt.text =dataArticle[position].createdAt
        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailKopiArticle::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataArticle.size
    }
}