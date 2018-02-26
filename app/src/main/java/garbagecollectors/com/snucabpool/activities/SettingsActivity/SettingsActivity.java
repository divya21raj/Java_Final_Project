package garbagecollectors.com.snucabpool.activities.SettingsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import garbagecollectors.com.snucabpool.R;
import garbagecollectors.com.snucabpool.activities.BaseActivity;
import garbagecollectors.com.snucabpool.activities.LoginActivity;

import static garbagecollectors.com.snucabpool.activities.BaseActivity.currentUser;
import static garbagecollectors.com.snucabpool.activities.BaseActivity.finalCurrentUser;

public class SettingsActivity extends AppCompatActivity
{
	DrawerLayout drawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		final ActionBar actionBar = getSupportActionBar();
		if(actionBar != null)
		{
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		drawerLayout = (DrawerLayout) findViewById(R.id.settings_layout);

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer);
		navigationView.setNavigationItemSelectedListener(menuItem ->
		{
			dealWithSelectedMenuItem(menuItem);
			drawerLayout.closeDrawers();

			return true;
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				setNavHeaderStuff();
				return true;

		}
		return super.onOptionsItemSelected(item);
	}

	private void setNavHeaderStuff()
	{
		TextView userNameOnHeader = (TextView) findViewById(R.id.header_username);
		userNameOnHeader.setText(finalCurrentUser.getName());

		TextView emailOnHeader = (TextView) findViewById(R.id.header_email);
		emailOnHeader.setText(currentUser.getEmail());
	}

	private void dealWithSelectedMenuItem(MenuItem menuItem)
	{
		switch (menuItem.getItemId())
		{
			case R.id.nav_settings:
				break;

			case R.id.nav_logout:
				BaseActivity.mAuth.signOut();
				finish();
				startActivity(new Intent(this, LoginActivity.class));
		}
	}
}
