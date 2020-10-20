package com.lukakordzaia.gamelandgeorgia.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.lukakordzaia.gamelandgeorgia.R
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebViewFragment : Fragment() {
    fun newInstance(webUrl: String?): WebViewFragment? {
        val frag = WebViewFragment()
        val args = Bundle()
        args.putString("webUrl", webUrl)
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments
        var singleGameId = bundle!!.getString("webUrl")

        var webView = activity!!.findViewById<View>(R.id.single_post_webView) as WebView

        webView.loadUrl("$singleGameId")

        webView.webViewClient = Mybrowser()
    }

    private class Mybrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}