// Generated code from Butter Knife. Do not modify!
package br.com.easyteste.view.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MapsFragment$$ViewBinder<T extends br.com.easyteste.view.fragment.MapsFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558555, "field 'fabMyLocation' and method 'tapOnRequestLocation'");
    target.fabMyLocation = finder.castView(view, 2131558555, "field 'fabMyLocation'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.tapOnRequestLocation();
        }
      });
    view = finder.findRequiredView(source, 2131558553, "field 'bottomSheet'");
    target.bottomSheet = finder.castView(view, 2131558553, "field 'bottomSheet'");
    view = finder.findRequiredView(source, 2131558534, "field 'recycler'");
    target.recycler = finder.castView(view, 2131558534, "field 'recycler'");
    view = finder.findRequiredView(source, 2131558554, "method 'openBottomSheet'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.openBottomSheet();
        }
      });
  }

  @Override public void unbind(T target) {
    target.fabMyLocation = null;
    target.bottomSheet = null;
    target.recycler = null;
  }
}
