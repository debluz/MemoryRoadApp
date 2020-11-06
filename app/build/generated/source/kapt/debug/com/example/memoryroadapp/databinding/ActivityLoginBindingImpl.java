package com.example.memoryroadapp.databinding;
import com.example.memoryroadapp.R;
import com.example.memoryroadapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBindingImpl extends ActivityLoginBinding implements com.example.memoryroadapp.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.emailTextInputLayout, 5);
        sViewsWithIds.put(R.id.textInputLayout, 6);
        sViewsWithIds.put(R.id.google_sign_in_button, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final com.google.android.material.textfield.TextInputEditText mboundView2;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener emailEditTextLoginandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.emailEditTextContent.getValue()
            //         is viewmodel.emailEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(emailEditTextLogin);
            // localize variables for thread safety
            // viewmodel.emailEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelEmailEditTextContent = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.emailEditTextContent != null
            boolean viewmodelEmailEditTextContentJavaLangObjectNull = false;
            // viewmodel.emailEditTextContent.getValue()
            java.lang.String viewmodelEmailEditTextContentGetValue = null;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AuthViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelEmailEditTextContent = viewmodel.getEmailEditTextContent();

                viewmodelEmailEditTextContentJavaLangObjectNull = (viewmodelEmailEditTextContent) != (null);
                if (viewmodelEmailEditTextContentJavaLangObjectNull) {




                    viewmodelEmailEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView2androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.passwordEditTextContent.getValue()
            //         is viewmodel.passwordEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView2);
            // localize variables for thread safety
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.passwordEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelPasswordEditTextContent = null;
            // viewmodel
            com.example.memoryroadapp.data.viewmodels.AuthViewModel viewmodel = mViewmodel;
            // viewmodel.passwordEditTextContent.getValue()
            java.lang.String viewmodelPasswordEditTextContentGetValue = null;
            // viewmodel.passwordEditTextContent != null
            boolean viewmodelPasswordEditTextContentJavaLangObjectNull = false;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelPasswordEditTextContent = viewmodel.getPasswordEditTextContent();

                viewmodelPasswordEditTextContentJavaLangObjectNull = (viewmodelPasswordEditTextContent) != (null);
                if (viewmodelPasswordEditTextContentJavaLangObjectNull) {




                    viewmodelPasswordEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ActivityLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ActivityLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (com.google.android.gms.common.SignInButton) bindings[7]
            , (com.google.android.material.button.MaterialButton) bindings[3]
            , (com.google.android.material.textview.MaterialTextView) bindings[4]
            , (com.google.android.material.textfield.TextInputLayout) bindings[6]
            );
        this.emailEditTextLogin.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (com.google.android.material.textfield.TextInputEditText) bindings[2];
        this.mboundView2.setTag(null);
        this.signInButton.setTag(null);
        this.signUpTextView.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.example.memoryroadapp.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
            setViewmodel((com.example.memoryroadapp.data.viewmodels.AuthViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewmodel(@Nullable com.example.memoryroadapp.data.viewmodels.AuthViewModel Viewmodel) {
        this.mViewmodel = Viewmodel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewmodelEmailEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewmodelPasswordEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewmodelEmailEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelEmailEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelPasswordEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelPasswordEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelEmailEditTextContent = null;
        java.lang.String viewmodelClickableContent = null;
        java.lang.String viewmodelPasswordEditTextContentGetValue = null;
        java.lang.String viewmodelEmailEditTextContentGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelPasswordEditTextContent = null;
        com.example.memoryroadapp.data.viewmodels.AuthViewModel viewmodel = mViewmodel;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.emailEditTextContent
                        viewmodelEmailEditTextContent = viewmodel.getEmailEditTextContent();
                    }
                    updateLiveDataRegistration(0, viewmodelEmailEditTextContent);


                    if (viewmodelEmailEditTextContent != null) {
                        // read viewmodel.emailEditTextContent.getValue()
                        viewmodelEmailEditTextContentGetValue = viewmodelEmailEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0xcL) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.clickableContent
                        viewmodelClickableContent = viewmodel.getClickableContent();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.passwordEditTextContent
                        viewmodelPasswordEditTextContent = viewmodel.getPasswordEditTextContent();
                    }
                    updateLiveDataRegistration(1, viewmodelPasswordEditTextContent);


                    if (viewmodelPasswordEditTextContent != null) {
                        // read viewmodel.passwordEditTextContent.getValue()
                        viewmodelPasswordEditTextContentGetValue = viewmodelPasswordEditTextContent.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emailEditTextLogin, viewmodelEmailEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.emailEditTextLogin, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, emailEditTextLoginandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView2, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView2androidTextAttrChanged);
            this.signInButton.setOnClickListener(mCallback5);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewmodelPasswordEditTextContentGetValue);
        }
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.onClickableContentClick(this.signUpTextView, viewmodelClickableContent);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewmodel != null
        boolean viewmodelJavaLangObjectNull = false;
        // viewmodel
        com.example.memoryroadapp.data.viewmodels.AuthViewModel viewmodel = mViewmodel;



        viewmodelJavaLangObjectNull = (viewmodel) != (null);
        if (viewmodelJavaLangObjectNull) {


            viewmodel.onSignInButtonClick();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewmodel.emailEditTextContent
        flag 1 (0x2L): viewmodel.passwordEditTextContent
        flag 2 (0x3L): viewmodel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}