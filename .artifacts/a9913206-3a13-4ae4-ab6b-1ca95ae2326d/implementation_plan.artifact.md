# Fix HTTP 403 and App Crash in Login Flow

This plan addresses the `retrofit2.HttpException: HTTP 403` error and the subsequent application crash reported by the user.

## User Review Required

> [!NOTE]
> The HTTP 403 Forbidden error is often caused by the server rejecting requests that lack a standard `User-Agent` header. I will add a default `User-Agent` to all network requests.

> [!IMPORTANT]
> I will also fix a bug in the `LoginViewModel` where the network call was executed outside of the `try-catch` block, which caused the application to crash when the error occurred.

## Proposed Changes

### Data Layer

#### [MODIFY] [RetrofitClient.kt](file:///Volumes/Big Boss/Android/AndroidStudioProjects/CRM/app/src/main/java/com/techshift/crm/data/api/RetrofitClient.kt)
- Add an `OkHttpClient` with `HttpLoggingInterceptor` for easier debugging.
- Add an interceptor to include a standard `User-Agent` header, which often resolves 403 Forbidden errors.

### UI Layer

#### [MODIFY] [LoginViewModel.kt](file:///Volumes/Big Boss/Android/AndroidStudioProjects/CRM/app/src/main/java/com/techshift/crm/ui/login/LoginViewModel.kt)
- Move the `authRepository.login` call inside the `try-catch` block to handle network exceptions gracefully and prevent app crashes.

## Verification Plan

### Manual Verification
- Run the application and attempt to login.
- Check the Logcat for "OkHttp" tags to see the detailed request/response logs.
- Verify that the app no longer crashes if the 403 error persists, and instead shows an error message.
