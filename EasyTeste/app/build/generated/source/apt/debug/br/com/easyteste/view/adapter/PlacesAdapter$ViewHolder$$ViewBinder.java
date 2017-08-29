// Generated code from Butter Knife. Do not modify!
package br.com.easyteste.view.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PlacesAdapter$ViewHolder$$ViewBinder<T extends br.com.easyteste.view.adapter.PlacesAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558579, "field 'name'");
    target.name = finder.castView(view, 2131558579, "field 'name'");
  }

  @Override public void unbind(T target) {
    target.name = null;
  }
}
