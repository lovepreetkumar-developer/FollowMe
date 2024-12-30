package com.techeytech.followme.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.techeytech.followme.R;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by androiddev on 9/10/2018.
 */

public class BackStackManager {
    private HashMap<String, Stack<Fragment>> backStack;
    private static BackStackManager _instance;
    private FragmentManager manager;
    private String currentTab;

    public String getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(String currentTab) {
        this.currentTab = currentTab;
    }

    private BackStackManager() {
        backStack = new HashMap<>();
    }

    public static BackStackManager getInstance(FragmentActivity context) {
        if (_instance == null) {
            _instance = new BackStackManager();

        }
        _instance.manager = context.getSupportFragmentManager();
        return _instance;
    }

    public HashMap<String, Stack<Fragment>> getBackStack() {
        return backStack;
    }

    public void clear() {
        _instance = null;
    }

    public void pushFragments(int containorid, String tag, Fragment fragment, boolean shouldAnimate) {
        currentTab = tag;
        FragmentTransaction ft = manager.beginTransaction();
        if (shouldAnimate)
            ft.setCustomAnimations(R.anim.alpha_undim,
                    R.anim.alpha_dim,
                    R.anim.alpha_undim,
                    R.anim.alpha_dim);

        if (backStack.containsKey(tag)) {
            Stack<Fragment> s = backStack.get(tag);
            assert s != null;
            ft.replace(containorid, s.lastElement());
        } else {
            Stack<Fragment> s = new Stack<>();
            s.push(fragment);
            backStack.put(tag, s);
            ft.replace(containorid, fragment);
        }

        ft.commit();


    }


    public void pushSubFragments(int containorid, String tag, Fragment fragment, boolean shouldAnimate) {
        currentTab = tag;
        FragmentTransaction ft = manager.beginTransaction();
        if (shouldAnimate)
            ft.setCustomAnimations(R.anim.alpha_undim,
                    R.anim.alpha_dim,
                    R.anim.alpha_undim,
                    R.anim.alpha_dim);
        if (backStack.containsKey(tag)) {
            Stack<Fragment> s = backStack.get(tag);
            s.push(fragment);
            ft.replace(containorid, s.lastElement());
        } else {
            Stack<Fragment> s = new Stack<>();
            s.push(fragment);
            backStack.put(tag, s);
            ft.replace(containorid, fragment);
        }

        ft.commit();
    }

    public void setCurrentFragments(int containorid) {
        if (currentTab != null) {
            if (backStack.containsKey(currentTab)) {
                FragmentTransaction ft = manager.beginTransaction();
                Stack<Fragment> s = backStack.get(currentTab);
                ft.replace(containorid, s.lastElement());
                ft.commit();
            }
        }
    }

    public void removeSubFragment(String tag, int containorid, boolean animate) {
        if (backStack.containsKey(tag)) {
            backStack.get(tag).pop();
            FragmentTransaction ft = manager.beginTransaction();
            if (animate)
                ft.setCustomAnimations(R.anim.alpha_undim,
                        R.anim.alpha_dim,
                        R.anim.alpha_undim,
                        R.anim.alpha_dim);
            ft.replace(containorid, backStack.get(tag).lastElement());
            ft.commit();
        }
    }

}
