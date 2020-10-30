package com.example.memoryroadapp.databinding;
import com.example.memoryroadapp.R;
import com.example.memoryroadapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddEditLocationBindingImpl extends ActivityAddEditLocationBinding implements com.example.memoryroadapp.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.scrollView, 10);
        sViewsWithIds.put(R.id.addImageFAB, 11);
        sViewsWithIds.put(R.id.nameInputLayout, 12);
        sViewsWithIds.put(R.id.descriptionInputLayout, 13);
        sViewsWithIds.put(R.id.latitudeInputLayout, 14);
        sViewsWithIds.put(R.id.longitudeInputLayout, 15);
        sViewsWithIds.put(R.id.diameterInputLayout, 16);
        sViewsWithIds.put(R.id.relativeLayout, 17);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView3;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView4;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView5;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView7;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener locationNameEditTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.nameEditTextContent.getValue()
            //         is viewmodel.nameEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(locationNameEditText);
            // localize variables for thread safety
            // viewmodel.nameEditTextContent != null
            boolean viewmodelNameEditTextContentJavaLangObjectNull = false;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.nameEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelNameEditTextContent = null;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;
            // viewmodel.nameEditTextContent.getValue()
            java.lang.String viewmodelNameEditTextContentGetValue = null;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelNameEditTextContent = viewmodel.getNameEditTextContent();

                viewmodelNameEditTextContentJavaLangObjectNull = (viewmodelNameEditTextContent) != (null);
                if (viewmodelNameEditTextContentJavaLangObjectNull) {




                    viewmodelNameEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.descriptionEditTextContent.getValue()
            //         is viewmodel.descriptionEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView3);
            // localize variables for thread safety
            // viewmodel.descriptionEditTextContent.getValue()
            java.lang.String viewmodelDescriptionEditTextContentGetValue = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.descriptionEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelDescriptionEditTextContent = null;
            // viewmodel.descriptionEditTextContent != null
            boolean viewmodelDescriptionEditTextContentJavaLangObjectNull = false;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelDescriptionEditTextContent = viewmodel.getDescriptionEditTextContent();

                viewmodelDescriptionEditTextContentJavaLangObjectNull = (viewmodelDescriptionEditTextContent) != (null);
                if (viewmodelDescriptionEditTextContentJavaLangObjectNull) {




                    viewmodelDescriptionEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView4androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.latitudeEditTextContent.getValue()
            //         is viewmodel.latitudeEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView4);
            // localize variables for thread safety
            // viewmodel.latitudeEditTextContent != null
            boolean viewmodelLatitudeEditTextContentJavaLangObjectNull = false;
            // viewmodel.latitudeEditTextContent.getValue()
            java.lang.String viewmodelLatitudeEditTextContentGetValue = null;
            // viewmodel.latitudeEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLatitudeEditTextContent = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelLatitudeEditTextContent = viewmodel.getLatitudeEditTextContent();

                viewmodelLatitudeEditTextContentJavaLangObjectNull = (viewmodelLatitudeEditTextContent) != (null);
                if (viewmodelLatitudeEditTextContentJavaLangObjectNull) {




                    viewmodelLatitudeEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView5androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.longitudeEditTextContent.getValue()
            //         is viewmodel.longitudeEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView5);
            // localize variables for thread safety
            // viewmodel.longitudeEditTextContent.getValue()
            java.lang.String viewmodelLongitudeEditTextContentGetValue = null;
            // viewmodel.longitudeEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLongitudeEditTextContent = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.longitudeEditTextContent != null
            boolean viewmodelLongitudeEditTextContentJavaLangObjectNull = false;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelLongitudeEditTextContent = viewmodel.getLongitudeEditTextContent();

                viewmodelLongitudeEditTextContentJavaLangObjectNull = (viewmodelLongitudeEditTextContent) != (null);
                if (viewmodelLongitudeEditTextContentJavaLangObjectNull) {




                    viewmodelLongitudeEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView7androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.diameterEditTextContent.getValue()
            //         is viewmodel.diameterEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView7);
            // localize variables for thread safety
            // viewmodel.diameterEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelDiameterEditTextContent = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.diameterEditTextContent != null
            boolean viewmodelDiameterEditTextContentJavaLangObjectNull = false;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;
            // viewmodel.diameterEditTextContent.getValue()
            java.lang.String viewmodelDiameterEditTextContentGetValue = null;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelDiameterEditTextContent = viewmodel.getDiameterEditTextContent();

                viewmodelDiameterEditTextContentJavaLangObjectNull = (viewmodelDiameterEditTextContent) != (null);
                if (viewmodelDiameterEditTextContentJavaLangObjectNull) {




                    viewmodelDiameterEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ActivityAddEditLocationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }
    private ActivityAddEditLocationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[11]
            , (com.google.android.material.textfield.TextInputLayout) bindings[13]
            , (com.google.android.material.textfield.TextInputLayout) bindings[16]
            , (android.widget.ImageView) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputEditText) bindings[2]
            , (android.widget.ImageButton) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[15]
            , (com.google.android.material.textfield.TextInputLayout) bindings[12]
            , (android.widget.RelativeLayout) bindings[17]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (androidx.core.widget.NestedScrollView) bindings[10]
            );
        this.imageView.setTag(null);
        this.locationNameEditText.setTag(null);
        this.locationSelectButton.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (com.google.android.material.textfield.TextInputEditText) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (com.google.android.material.textfield.TextInputEditText) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (com.google.android.material.textfield.TextInputEditText) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView7 = (com.google.android.material.textfield.TextInputEditText) bindings[7];
        this.mboundView7.setTag(null);
        this.resetTextView.setTag(null);
        this.saveTextView.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.example.memoryroadapp.generated.callback.OnClickListener(this, 2);
        mCallback3 = new com.example.memoryroadapp.generated.callback.OnClickListener(this, 3);
        mCallback1 = new com.example.memoryroadapp.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewmodel == variableId) {
            setViewmodel((com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewmodel(@Nullable com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel Viewmodel) {
        this.mViewmodel = Viewmodel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewmodelLatitudeEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewmodelLongitudeEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewmodelImageBitmap((androidx.lifecycle.LiveData<android.graphics.Bitmap>) object, fieldId);
            case 3 :
                return onChangeViewmodelDiameterEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewmodelNameEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 5 :
                return onChangeViewmodelDescriptionEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewmodelLatitudeEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelLatitudeEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelLongitudeEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelLongitudeEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelImageBitmap(androidx.lifecycle.LiveData<android.graphics.Bitmap> ViewmodelImageBitmap, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelDiameterEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelDiameterEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelNameEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelNameEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelDescriptionEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelDescriptionEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String viewmodelLongitudeEditTextContentGetValue = null;
        java.lang.String viewmodelLatitudeEditTextContentGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLatitudeEditTextContent = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLongitudeEditTextContent = null;
        androidx.lifecycle.LiveData<android.graphics.Bitmap> viewmodelImageBitmap = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelDiameterEditTextContent = null;
        java.lang.String viewmodelDescriptionEditTextContentGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelNameEditTextContent = null;
        android.graphics.Bitmap viewmodelImageBitmapGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelDescriptionEditTextContent = null;
        java.lang.String viewmodelNameEditTextContentGetValue = null;
        java.lang.String viewmodelDiameterEditTextContentGetValue = null;
        com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;

        if ((dirtyFlags & 0xffL) != 0) {


            if ((dirtyFlags & 0xc1L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.latitudeEditTextContent
                        viewmodelLatitudeEditTextContent = viewmodel.getLatitudeEditTextContent();
                    }
                    updateLiveDataRegistration(0, viewmodelLatitudeEditTextContent);


                    if (viewmodelLatitudeEditTextContent != null) {
                        // read viewmodel.latitudeEditTextContent.getValue()
                        viewmodelLatitudeEditTextContentGetValue = viewmodelLatitudeEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0xc2L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.longitudeEditTextContent
                        viewmodelLongitudeEditTextContent = viewmodel.getLongitudeEditTextContent();
                    }
                    updateLiveDataRegistration(1, viewmodelLongitudeEditTextContent);


                    if (viewmodelLongitudeEditTextContent != null) {
                        // read viewmodel.longitudeEditTextContent.getValue()
                        viewmodelLongitudeEditTextContentGetValue = viewmodelLongitudeEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0xc4L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.imageBitmap
                        viewmodelImageBitmap = viewmodel.getImageBitmap();
                    }
                    updateLiveDataRegistration(2, viewmodelImageBitmap);


                    if (viewmodelImageBitmap != null) {
                        // read viewmodel.imageBitmap.getValue()
                        viewmodelImageBitmapGetValue = viewmodelImageBitmap.getValue();
                    }
            }
            if ((dirtyFlags & 0xc8L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.diameterEditTextContent
                        viewmodelDiameterEditTextContent = viewmodel.getDiameterEditTextContent();
                    }
                    updateLiveDataRegistration(3, viewmodelDiameterEditTextContent);


                    if (viewmodelDiameterEditTextContent != null) {
                        // read viewmodel.diameterEditTextContent.getValue()
                        viewmodelDiameterEditTextContentGetValue = viewmodelDiameterEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0xd0L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.nameEditTextContent
                        viewmodelNameEditTextContent = viewmodel.getNameEditTextContent();
                    }
                    updateLiveDataRegistration(4, viewmodelNameEditTextContent);


                    if (viewmodelNameEditTextContent != null) {
                        // read viewmodel.nameEditTextContent.getValue()
                        viewmodelNameEditTextContentGetValue = viewmodelNameEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0xe0L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.descriptionEditTextContent
                        viewmodelDescriptionEditTextContent = viewmodel.getDescriptionEditTextContent();
                    }
                    updateLiveDataRegistration(5, viewmodelDescriptionEditTextContent);


                    if (viewmodelDescriptionEditTextContent != null) {
                        // read viewmodel.descriptionEditTextContent.getValue()
                        viewmodelDescriptionEditTextContentGetValue = viewmodelDescriptionEditTextContent.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xc4L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.loadImage(this.imageView, viewmodelImageBitmapGetValue);
        }
        if ((dirtyFlags & 0xd0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.locationNameEditText, viewmodelNameEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.locationNameEditText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, locationNameEditTextandroidTextAttrChanged);
            this.locationSelectButton.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView3androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView4, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView4androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView5, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView5androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView7, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView7androidTextAttrChanged);
            this.resetTextView.setOnClickListener(mCallback2);
            this.saveTextView.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0xe0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, viewmodelDescriptionEditTextContentGetValue);
        }
        if ((dirtyFlags & 0xc1L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, viewmodelLatitudeEditTextContentGetValue);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, viewmodelLongitudeEditTextContentGetValue);
        }
        if ((dirtyFlags & 0xc8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, viewmodelDiameterEditTextContentGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // viewmodel != null
                boolean viewmodelJavaLangObjectNull = false;
                // viewmodel
                com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



                viewmodelJavaLangObjectNull = (viewmodel) != (null);
                if (viewmodelJavaLangObjectNull) {


                    viewmodel.onResetClick();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // viewmodel != null
                boolean viewmodelJavaLangObjectNull = false;
                // viewmodel
                com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



                viewmodelJavaLangObjectNull = (viewmodel) != (null);
                if (viewmodelJavaLangObjectNull) {


                    viewmodel.onSaveClick();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewmodel != null
                boolean viewmodelJavaLangObjectNull = false;
                // viewmodel
                com.example.memoryroadapp.data.viewmodels.AddEditLocationViewModel viewmodel = mViewmodel;



                viewmodelJavaLangObjectNull = (viewmodel) != (null);
                if (viewmodelJavaLangObjectNull) {


                    viewmodel.onSelectLocationButtonClick();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewmodel.latitudeEditTextContent
        flag 1 (0x2L): viewmodel.longitudeEditTextContent
        flag 2 (0x3L): viewmodel.imageBitmap
        flag 3 (0x4L): viewmodel.diameterEditTextContent
        flag 4 (0x5L): viewmodel.nameEditTextContent
        flag 5 (0x6L): viewmodel.descriptionEditTextContent
        flag 6 (0x7L): viewmodel
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}