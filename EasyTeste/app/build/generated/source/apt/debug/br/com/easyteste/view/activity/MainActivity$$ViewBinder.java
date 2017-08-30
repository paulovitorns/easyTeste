// Generated code from Butter Knife. Do not modify!
package br.com.easyteste.view.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends br.com.easyteste.view.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558533, "field 'frameLayout'");
    target.frameLayout = finder.castView(view, 2131558533, "field 'frameLayout'");
    view = finder.findRequiredView(source, 2131558532, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558532, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558534, "field 'containerSearch'");
    target.containerSearch = finder.castView(view, 2131558534, "field 'containerSearch'");
    view = finder.findRequiredView(source, 2131558536, "field 'emptyState'");
    target.emptyState = finder.castView(view, 2131558536, "field 'emptyState'");
    view = finder.findRequiredView(source, 2131558535, "field 'recycler'");
    target.recycler = finder.castView(view, 2131558535, "field 'recycler'");
  }

  @Override public void unbind(T target) {
    target.frameLayout = null;
    target.toolbar = null;
    target.containerSearch = null;
    target.emptyState = null;
    target.recycler = null;
  }
}
