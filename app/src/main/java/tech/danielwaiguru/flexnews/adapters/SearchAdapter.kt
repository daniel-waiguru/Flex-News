package tech.danielwaiguru.flexnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*
import tech.danielwaiguru.flexnews.R
import tech.danielwaiguru.flexnews.models.Article

class SearchAdapter: PagingDataAdapter<Article, MainViewHolder>(ARTICLE_COMPARATOR) {
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null){
            holder.itemView.apply {
                Picasso.get().load(article.url)
                    .error(R.drawable.ic_broken_image)
                    .centerCrop()
                    .into(articleImage)
                articleTitle.text = article.title
                articleSource.text = article.source.name
                articlePublishedAt.text = article.publishedAt
                articleDescription.text = article.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_item, parent, false
            )
        )
    }
    companion object {
        val ARTICLE_COMPARATOR = object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return  oldItem == newItem
            }
        }
    }
}