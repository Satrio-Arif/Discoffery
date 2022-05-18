// Generated by view binder compiler. Do not edit!
package com.project.discoffery.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.project.discoffery.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCameraBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView captureImage;

  @NonNull
  public final ImageView switchCamera;

  @NonNull
  public final PreviewView viewFinder;

  private ActivityCameraBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView captureImage,
      @NonNull ImageView switchCamera, @NonNull PreviewView viewFinder) {
    this.rootView = rootView;
    this.captureImage = captureImage;
    this.switchCamera = switchCamera;
    this.viewFinder = viewFinder;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCameraBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCameraBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_camera, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCameraBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.captureImage;
      ImageView captureImage = ViewBindings.findChildViewById(rootView, id);
      if (captureImage == null) {
        break missingId;
      }

      id = R.id.switchCamera;
      ImageView switchCamera = ViewBindings.findChildViewById(rootView, id);
      if (switchCamera == null) {
        break missingId;
      }

      id = R.id.viewFinder;
      PreviewView viewFinder = ViewBindings.findChildViewById(rootView, id);
      if (viewFinder == null) {
        break missingId;
      }

      return new ActivityCameraBinding((ConstraintLayout) rootView, captureImage, switchCamera,
          viewFinder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
