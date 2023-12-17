package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.PostDelegation
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.model.PostModel.PostSuspension
import com.original.sense.psit.model.PostModel.PostTokenAccess
import com.original.sense.psit.model.PostModel.PostTokenRefresh
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.PostModel.getDelegationPost
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.LogoutResponse
import com.original.sense.psit.model.ResponseModel.ResponseEditProfile
import com.original.sense.psit.model.ResponseModel.ResponseGetDelegation
import com.original.sense.psit.model.ResponseModel.ResponsePostDelegation
import com.original.sense.psit.model.ResponseModel.ResponseTokenAccess
import com.original.sense.psit.model.ResponseModel.ResponseTokenRefresh
import com.original.sense.psit.model.ResponseModel.TempRegister
import com.original.sense.psit.model.ResponseModel.UserProfileDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PsitViewModel @Inject constructor(private val repository: PsitRepository, private val tokenStore: TokenStore) : ViewModel() {



    private val _registrationStatus = MutableLiveData<TempRegister?>()
    val registrationStatus: LiveData<TempRegister?> = _registrationStatus

    private val _loginStatus = MutableLiveData<LoginResponse?>()
    val loginStatus: LiveData<LoginResponse?> = _loginStatus

    private val _changePassword = MutableLiveData<ChangePasswordResponse?>()
    val changePassword: LiveData<ChangePasswordResponse?> = _changePassword




    private val _getDelegation = MutableLiveData<ResponseGetDelegation?>()
    val getDelegation : LiveData<ResponseGetDelegation?> = _getDelegation

    private val _getSuspension = MutableLiveData<ResponseGetDelegation?>()
    val getSuspension : LiveData<ResponseGetDelegation?> = _getSuspension

    private val _logoutResponse = MutableLiveData<LogoutResponse?>()
    val logoutResponse : LiveData<LogoutResponse?> = _logoutResponse

    private val _updateUserProfileData = MutableLiveData<ResponseEditProfile?>()
    val updateUserProfileData : LiveData<ResponseEditProfile?> = _updateUserProfileData

    private val _getUserProfileData = MutableLiveData<UserProfileDetail?>()
    val getUserProfileData : LiveData<UserProfileDetail?> = _getUserProfileData


    private val _postTokenRefresh = MutableLiveData<ResponseTokenRefresh?>()
    val postTokenRefresh : LiveData<ResponseTokenRefresh?> = _postTokenRefresh

    private val _postTokenVerify = MutableLiveData<ResponseTokenAccess?>()
    val postTokenVerify : LiveData<ResponseTokenAccess?> = _postTokenVerify

    private val _getStudent = MutableLiveData<GetStudentResponse?>()
    val getStudent: LiveData<GetStudentResponse?> = _getStudent


    fun getStudent(accessToken:String , getStudentPost: GetStudentPost) {
        viewModelScope.launch {
            val result = repository.getStudent(accessToken,getStudentPost)
            _getStudent.postValue(result)
        }
    }

    fun getStudent2(
        accessToken: String ,
        getStudentPost: GetStudentPost ,
        studentListViewModel: StudentListViewModel
    ) {
        viewModelScope.launch {
            val result = repository.getStudent(accessToken,getStudentPost)
            Log.e("okhttp","$result")

                when (result?.status_code) {
                    200 -> studentListViewModel.updateStudentList(result.responseData!!.name,getStudentPost.id)
                    400 -> {
                        studentListViewModel.updateStudentList("Incorrect Student Id",getStudentPost.id)
                    }
                    500 -> {
                        studentListViewModel.updateStudentList("Internal Server Error",getStudentPost.id)
                    }
                    else -> {
                        studentListViewModel.updateStudentList("Network Error",getStudentPost.id)
                    }
                }
        }
    }

    fun postTokenRefresh(postTokenRefresh: PostTokenRefresh) {
        viewModelScope.launch {
            val result = repository.postTokenRefresh(postTokenRefresh)
            _postTokenRefresh.postValue(result)
        }
    }

    fun postTokenVerify(postTokenAccess: PostTokenAccess) {
        viewModelScope.launch {
            val result = repository.postTokenVerify(postTokenAccess)
            _postTokenVerify.postValue(result)
        }
    }

    fun updateUserProfile(access: String,postEditProfile: PostEditProfile) {
        viewModelScope.launch {
            val result = repository.updateUserProfile(access,postEditProfile)
            _updateUserProfileData.postValue(result)
        }
    }

    fun getUserProfileData(access: String) {
        viewModelScope.launch {
            val result = repository.getUserProfileData(access)
            _getUserProfileData.postValue(result)
        }
    }

    fun getSuspension(access:String,getDelegationPost: getDelegationPost) {
        viewModelScope.launch {
            val result = repository.getSuspension(access,getDelegationPost)
            _getSuspension.postValue(result)
        }
    }

    fun getDelegation(access: String,getDelegationPost: getDelegationPost) {
        viewModelScope.launch {
            val result = repository.getDelegation(access,getDelegationPost)
            _getDelegation.postValue(result)
        }
    }




    fun registerUser(tempRegisterPost: TempRegisterPost) {
        viewModelScope.launch {
            val result = repository.registerUser(tempRegisterPost)
            _registrationStatus.postValue(result)
        }
    }

    fun loginUser(loginPost: LoginPost) {
        viewModelScope.launch {
            val result = repository.loginUser(loginPost)
            if (!result!!.error) {

                val accessToken = result.responseData.token.access
                val refreshToken = result.responseData.token.refresh

                Log.d("Repository - access token", accessToken)
                Log.d("Repository - refresh token", refreshToken)

                // Save tokens to DataStore upon successful login
                tokenStore.saveTokens(accessToken, refreshToken)
            }

            _loginStatus.postValue(result)

        }
    }

    fun changePassword(accessToken:String,changePasswordPost: ChangePasswordPost) {
        viewModelScope.launch {
            val result = repository.changePassword(accessToken,changePasswordPost)
            _changePassword.postValue(result)
        }
    }




    fun logOut(refreshToken:String) {
        viewModelScope.launch {
            val result = repository.logOut(refreshToken)

            _logoutResponse.postValue(result)
        }
    }

    suspend fun clearTokens() {
        tokenStore.saveTokens("", "") // Set tokens to empty strings or null as needed
    }

}
