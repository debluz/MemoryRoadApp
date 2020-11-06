package com.example.memoryroadapp.databinding;
import com.example.memoryroadapp.R;
import com.example.memoryroadapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivitySignUpBindingImpl extends ActivitySignUpBinding implements com.example.memoryroadapp.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener emailEditTextRegistrationandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.emailEditTextContent.getValue()
            //         is viewmodel.emailEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(emailEditTextRegistration);
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
            com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;



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
    private androidx.databinding.InverseBindingListener firstNameEditTextRegistrationandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.firstNameEditTextContent.getValue()
            //         is viewmodel.firstNameEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(firstNameEditTextRegistration);
            // localize variables for thread safety
            // viewmodel.firstNameEditTextContent != null
            boolean viewmodelFirstNameEditTextContentJavaLangObjectNull = false;
            // viewmodel.firstNameEditTextContent.getValue()
            java.lang.String viewmodelFirstNameEditTextContentGetValue = null;
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.firstNameEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelFirstNameEditTextContent = null;
            // viewmodel
            com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelFirstNameEditTextContent = viewmodel.getFirstNameEditTextContent();

                viewmodelFirstNameEditTextContentJavaLangObjectNull = (viewmodelFirstNameEditTextContent) != (null);
                if (viewmodelFirstNameEditTextContentJavaLangObjectNull) {




                    viewmodelFirstNameEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener lastNameEditTextRegistrationandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.lastNameEditTextContent.getValue()
            //         is viewmodel.lastNameEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(lastNameEditTextRegistration);
            // localize variables for thread safety
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.lastNameEditTextContent != null
            boolean viewmodelLastNameEditTextContentJavaLangObjectNull = false;
            // viewmodel.lastNameEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLastNameEditTextContent = null;
            // viewmodel.lastNameEditTextContent.getValue()
            java.lang.String viewmodelLastNameEditTextContentGetValue = null;
            // viewmodel
            com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;



            viewmodelJavaLangObjectNull = (viewmodel) != (null);
            if (viewmodelJavaLangObjectNull) {


                viewmodelLastNameEditTextContent = viewmodel.getLastNameEditTextContent();

                viewmodelLastNameEditTextContentJavaLangObjectNull = (viewmodelLastNameEditTextContent) != (null);
                if (viewmodelLastNameEditTextContentJavaLangObjectNull) {




                    viewmodelLastNameEditTextContent.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener passwordEditTextRegistrationandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewmodel.passwordEditTextContent.getValue()
            //         is viewmodel.passwordEditTextContent.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(passwordEditTextRegistration);
            // localize variables for thread safety
            // viewmodel != null
            boolean viewmodelJavaLangObjectNull = false;
            // viewmodel.passwordEditTextContent
            androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelPasswordEditTextContent = null;
            // viewmodel
            com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;
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

    public ActivitySignUpBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivitySignUpBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 9
            , (com.google.android.material.textfield.TextInputEditText) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[1]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (com.google.android.material.textfield.TextInputEditText) bindings[4]
            , (com.google.android.material.textfield.TextInputLayout) bindings[3]
            , (com.google.android.material.button.MaterialButton) bindings[9]
            );
        this.emailEditTextRegistration.setTag(null);
        this.emailTextInputLayoutRegistration.setTag(null);
        this.firstNameEditTextRegistration.setTag(null);
        this.firstNameInputLayoutRegistration.setTag(null);
        this.lastNameEditTextRegistration.setTag(null);
        this.lastNameInputLayoutRegistration.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.passwordEditTextRegistration.setTag(null);
        this.passwordInputLayoutRegistration.setTag(null);
        this.signUpButton.setTag(null);
        setRootTag(root);
        // listeners
        mCallback4 = new com.example.memoryroadapp.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x400L;
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
            setViewmodel((com.example.memoryroadapp.data.SignUpViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewmodel(@Nullable com.example.memoryroadapp.data.SignUpViewModel Viewmodel) {
        this.mViewmodel = Viewmodel;
        synchronized(this) {
            mDirtyFlags |= 0x200L;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewmodelValidPassword((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeViewmodelFirstNameEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewmodelValidEmail((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeViewmodelEmailEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeViewmodelValidFirstName((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 5 :
                return onChangeViewmodelEnabled((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 6 :
                return onChangeViewmodelLastNameEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 7 :
                return onChangeViewmodelPasswordEditTextContent((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 8 :
                return onChangeViewmodelValidLastName((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewmodelValidPassword(androidx.lifecycle.LiveData<java.lang.Boolean> ViewmodelValidPassword, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelFirstNameEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelFirstNameEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelValidEmail(androidx.lifecycle.LiveData<java.lang.Boolean> ViewmodelValidEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelEmailEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelEmailEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelValidFirstName(androidx.lifecycle.LiveData<java.lang.Boolean> ViewmodelValidFirstName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelEnabled(androidx.lifecycle.LiveData<java.lang.Boolean> ViewmodelEnabled, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelLastNameEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelLastNameEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelPasswordEditTextContent(androidx.lifecycle.MutableLiveData<java.lang.String> ViewmodelPasswordEditTextContent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewmodelValidLastName(androidx.lifecycle.LiveData<java.lang.Boolean> ViewmodelValidLastName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
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
        java.lang.String viewmodelErrorEmail = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewmodelValidPassword = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewmodelEnabledGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelFirstNameEditTextContent = null;
        android.text.TextWatcher viewmodelFirstNameTextWatcher = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewmodelValidFirstNameGetValue = false;
        java.lang.String viewmodelPasswordEditTextContentGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewmodelValidEmail = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewmodelValidEmailGetValue = false;
        java.lang.String viewmodelErrorPassword = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewmodelValidLastNameGetValue = false;
        java.lang.String viewmodelErrorName = null;
        java.lang.String viewmodelEmailEditTextContentGetValue = null;
        android.text.TextWatcher viewmodelEmailTextWatcher = null;
        java.lang.Boolean viewmodelEnabledGetValue = null;
        java.lang.String viewmodelFirstNameEditTextContentGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelEmailEditTextContent = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewmodelValidFirstName = null;
        android.text.TextWatcher viewmodelLastNameTextWatcher = null;
        java.lang.String viewmodelLastNameEditTextContentGetValue = null;
        java.lang.Boolean viewmodelValidPasswordGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewmodelEnabled = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelLastNameEditTextContent = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewmodelPasswordEditTextContent = null;
        java.lang.Boolean viewmodelValidLastNameGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewmodelValidLastName = null;
        java.lang.Boolean viewmodelValidFirstNameGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewmodelValidPasswordGetValue = false;
        android.text.TextWatcher viewmodelPasswordTextWatcher = null;
        java.lang.Boolean viewmodelValidEmailGetValue = null;
        com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;

        if ((dirtyFlags & 0x7ffL) != 0) {


            if ((dirtyFlags & 0x604L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.errorEmail
                        viewmodelErrorEmail = viewmodel.getErrorEmail();
                        // read viewmodel.validEmail
                        viewmodelValidEmail = viewmodel.getValidEmail();
                    }
                    updateLiveDataRegistration(2, viewmodelValidEmail);


                    if (viewmodelValidEmail != null) {
                        // read viewmodel.validEmail.getValue()
                        viewmodelValidEmailGetValue = viewmodelValidEmail.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewmodel.validEmail.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewmodelValidEmailGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewmodelValidEmailGetValue);
            }
            if ((dirtyFlags & 0x601L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.validPassword
                        viewmodelValidPassword = viewmodel.getValidPassword();
                        // read viewmodel.errorPassword
                        viewmodelErrorPassword = viewmodel.getErrorPassword();
                    }
                    updateLiveDataRegistration(0, viewmodelValidPassword);


                    if (viewmodelValidPassword != null) {
                        // read viewmodel.validPassword.getValue()
                        viewmodelValidPasswordGetValue = viewmodelValidPassword.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewmodel.validPassword.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewmodelValidPasswordGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewmodelValidPasswordGetValue);
            }
            if ((dirtyFlags & 0x602L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.firstNameEditTextContent
                        viewmodelFirstNameEditTextContent = viewmodel.getFirstNameEditTextContent();
                    }
                    updateLiveDataRegistration(1, viewmodelFirstNameEditTextContent);


                    if (viewmodelFirstNameEditTextContent != null) {
                        // read viewmodel.firstNameEditTextContent.getValue()
                        viewmodelFirstNameEditTextContentGetValue = viewmodelFirstNameEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0x600L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.firstNameTextWatcher
                        viewmodelFirstNameTextWatcher = viewmodel.getFirstNameTextWatcher();
                        // read viewmodel.emailTextWatcher
                        viewmodelEmailTextWatcher = viewmodel.getEmailTextWatcher();
                        // read viewmodel.lastNameTextWatcher
                        viewmodelLastNameTextWatcher = viewmodel.getLastNameTextWatcher();
                        // read viewmodel.passwordTextWatcher
                        viewmodelPasswordTextWatcher = viewmodel.getPasswordTextWatcher();
                    }
            }
            if ((dirtyFlags & 0x710L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.errorName
                        viewmodelErrorName = viewmodel.getErrorName();
                    }
            }
            if ((dirtyFlags & 0x608L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.emailEditTextContent
                        viewmodelEmailEditTextContent = viewmodel.getEmailEditTextContent();
                    }
                    updateLiveDataRegistration(3, viewmodelEmailEditTextContent);


                    if (viewmodelEmailEditTextContent != null) {
                        // read viewmodel.emailEditTextContent.getValue()
                        viewmodelEmailEditTextContentGetValue = viewmodelEmailEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0x610L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.validFirstName
                        viewmodelValidFirstName = viewmodel.getValidFirstName();
                    }
                    updateLiveDataRegistration(4, viewmodelValidFirstName);


                    if (viewmodelValidFirstName != null) {
                        // read viewmodel.validFirstName.getValue()
                        viewmodelValidFirstNameGetValue = viewmodelValidFirstName.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewmodel.validFirstName.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewmodelValidFirstNameGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewmodelValidFirstNameGetValue);
            }
            if ((dirtyFlags & 0x620L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.enabled
                        viewmodelEnabled = viewmodel.getEnabled();
                    }
                    updateLiveDataRegistration(5, viewmodelEnabled);


                    if (viewmodelEnabled != null) {
                        // read viewmodel.enabled.getValue()
                        viewmodelEnabledGetValue = viewmodelEnabled.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewmodel.enabled.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewmodelEnabledGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewmodelEnabledGetValue);
            }
            if ((dirtyFlags & 0x640L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.lastNameEditTextContent
                        viewmodelLastNameEditTextContent = viewmodel.getLastNameEditTextContent();
                    }
                    updateLiveDataRegistration(6, viewmodelLastNameEditTextContent);


                    if (viewmodelLastNameEditTextContent != null) {
                        // read viewmodel.lastNameEditTextContent.getValue()
                        viewmodelLastNameEditTextContentGetValue = viewmodelLastNameEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0x680L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.passwordEditTextContent
                        viewmodelPasswordEditTextContent = viewmodel.getPasswordEditTextContent();
                    }
                    updateLiveDataRegistration(7, viewmodelPasswordEditTextContent);


                    if (viewmodelPasswordEditTextContent != null) {
                        // read viewmodel.passwordEditTextContent.getValue()
                        viewmodelPasswordEditTextContentGetValue = viewmodelPasswordEditTextContent.getValue();
                    }
            }
            if ((dirtyFlags & 0x700L) != 0) {

                    if (viewmodel != null) {
                        // read viewmodel.validLastName
                        viewmodelValidLastName = viewmodel.getValidLastName();
                    }
                    updateLiveDataRegistration(8, viewmodelValidLastName);


                    if (viewmodelValidLastName != null) {
                        // read viewmodel.validLastName.getValue()
                        viewmodelValidLastNameGetValue = viewmodelValidLastName.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewmodel.validLastName.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewmodelValidLastNameGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewmodelValidLastNameGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x608L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emailEditTextRegistration, viewmodelEmailEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x600L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.addTextWatcher(this.emailEditTextRegistration, viewmodelEmailTextWatcher);
            com.example.memoryroadapp.util.BindingAdaptersKt.addTextWatcher(this.firstNameEditTextRegistration, viewmodelFirstNameTextWatcher);
            com.example.memoryroadapp.util.BindingAdaptersKt.addTextWatcher(this.lastNameEditTextRegistration, viewmodelLastNameTextWatcher);
            com.example.memoryroadapp.util.BindingAdaptersKt.addTextWatcher(this.passwordEditTextRegistration, viewmodelPasswordTextWatcher);
        }
        if ((dirtyFlags & 0x400L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.emailEditTextRegistration, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, emailEditTextRegistrationandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.firstNameEditTextRegistration, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, firstNameEditTextRegistrationandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.lastNameEditTextRegistration, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, lastNameEditTextRegistrationandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.passwordEditTextRegistration, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, passwordEditTextRegistrationandroidTextAttrChanged);
            this.signUpButton.setOnClickListener(mCallback4);
        }
        if ((dirtyFlags & 0x604L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.checkIfValid(this.emailTextInputLayoutRegistration, androidxDatabindingViewDataBindingSafeUnboxViewmodelValidEmailGetValue, viewmodelErrorEmail);
        }
        if ((dirtyFlags & 0x602L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.firstNameEditTextRegistration, viewmodelFirstNameEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x610L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.checkIfValid(this.firstNameInputLayoutRegistration, androidxDatabindingViewDataBindingSafeUnboxViewmodelValidFirstNameGetValue, viewmodelErrorName);
        }
        if ((dirtyFlags & 0x640L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.lastNameEditTextRegistration, viewmodelLastNameEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x700L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.checkIfValid(this.lastNameInputLayoutRegistration, androidxDatabindingViewDataBindingSafeUnboxViewmodelValidLastNameGetValue, viewmodelErrorName);
        }
        if ((dirtyFlags & 0x680L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.passwordEditTextRegistration, viewmodelPasswordEditTextContentGetValue);
        }
        if ((dirtyFlags & 0x601L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.checkIfValid(this.passwordInputLayoutRegistration, androidxDatabindingViewDataBindingSafeUnboxViewmodelValidPasswordGetValue, viewmodelErrorPassword);
        }
        if ((dirtyFlags & 0x620L) != 0) {
            // api target 1

            com.example.memoryroadapp.util.BindingAdaptersKt.checkIfFormValid(this.signUpButton, androidxDatabindingViewDataBindingSafeUnboxViewmodelEnabledGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewmodel != null
        boolean viewmodelJavaLangObjectNull = false;
        // viewmodel
        com.example.memoryroadapp.data.SignUpViewModel viewmodel = mViewmodel;



        viewmodelJavaLangObjectNull = (viewmodel) != (null);
        if (viewmodelJavaLangObjectNull) {


            viewmodel.onSignUpButtonClick();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewmodel.validPassword
        flag 1 (0x2L): viewmodel.firstNameEditTextContent
        flag 2 (0x3L): viewmodel.validEmail
        flag 3 (0x4L): viewmodel.emailEditTextContent
        flag 4 (0x5L): viewmodel.validFirstName
        flag 5 (0x6L): viewmodel.enabled
        flag 6 (0x7L): viewmodel.lastNameEditTextContent
        flag 7 (0x8L): viewmodel.passwordEditTextContent
        flag 8 (0x9L): viewmodel.validLastName
        flag 9 (0xaL): viewmodel
        flag 10 (0xbL): null
    flag mapping end*/
    //end
}