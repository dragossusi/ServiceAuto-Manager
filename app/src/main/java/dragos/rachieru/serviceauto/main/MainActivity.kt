package dragos.rachieru.serviceauto.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dragos.rachieru.serviceauto.BaseActivity
import dragos.rachieru.serviceauto.R
import dragos.rachieru.serviceauto.entities.IssueRequest
import dragos.rachieru.serviceauto.entities.enums.RequestStatus
import dragos.rachieru.serviceauto.extensions.isLoggedIn
import dragos.rachieru.serviceauto.login.LoginActivity
import dragos.rachieru.serviceauto.main.adapter.RequestAdapter
import dragos.rachieru.serviceauto.main.adapter.RequestStatusChanger
import dragos.rachieru.serviceauto.status.StatusChangeDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MainView, RequestStatusChanger {

    lateinit var adapter: RequestAdapter
    lateinit var presenter: MainPresenter
    var currentStatus: RequestStatus = RequestStatus.PENDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            adapter = RequestAdapter(this)
            recycler_requests.adapter = adapter
            recycler_requests.layoutManager = LinearLayoutManager(this)
            presenter = MainPresenter(this, api)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            presenter.getRequests(RequestStatus.PENDING)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.dispose()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_requests -> {
                adapter.clear()
                currentStatus = RequestStatus.PENDING
                presenter.getRequests(RequestStatus.PENDING)
            }
            R.id.nav_pending -> {
                adapter.clear()
                currentStatus = RequestStatus.ONGOING
                presenter.getRequests(RequestStatus.ONGOING)
            }
            R.id.nav_completed -> {
                adapter.clear()
                currentStatus = RequestStatus.COMPLETED
                presenter.getRequests(RequestStatus.COMPLETED)
            }
            R.id.nav_logout -> {
                isLoggedIn = false
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun changeAt(issue: IssueRequest, position: Int) {
        val dialogFragment = StatusChangeDialog()
        dialogFragment.listener = {
            issue.status = it
            presenter.setStatus(issue, position)
        }
        dialogFragment.status = currentStatus
        dialogFragment.show(supportFragmentManager, "tag")
    }

    override fun onStatusChanged(position: Int) {
        adapter.removeAt(position)
    }

    override fun onRequests(t: MutableList<IssueRequest>) {
        adapter.addAll(t)
    }
}
