package com.tolgahantutar.mvvmsampleapp.ui.home.quotes

import androidx.databinding.Bindable
import com.tolgahantutar.mvvmsampleapp.R
import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote
import com.tolgahantutar.mvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
    viewBinding.setQuote(quote)
    }

}