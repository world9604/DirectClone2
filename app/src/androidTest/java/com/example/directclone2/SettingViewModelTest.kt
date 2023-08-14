package com.example.directclone2

import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.viewmodel.SettingViewModel
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.File

/**
 * 요약
 * - testImplementation 구성을 사용하여 종속 항목이 애플리케이션 코드가 아닌 로컬 테스트 소스 코드에 적용됨을 나타냅니다.
 * - 테스트를 세 가지 시나리오(성공 경로, 오류 경로, 경계 사례)로 분류하는 것을 목표로 합니다.
 * - 좋은 단위 테스트에는 집중, 이해 가능, 확정성, 독립형이라는 적어도 4가지 특성이 있습니다.
 * - 테스트 메서드는 개별적으로 실행되어 변경 가능한 테스트 인스턴스 상태로 인한 예상치 못한 부작용을 방지합니다.
 * - 기본적으로 JUnit은 각 테스트 메서드가 실행되기 전에 테스트 클래스의 새 인스턴스를 만듭니다.
 * - 코드 적용 범위는 앱을 구성하는 클래스, 메서드, 코드 줄을 적절하게 테스트하는지 확인하는 데 핵심 역할을 합니다.
 */
/**
 * 다음 사항이 궁금할 수 있습니다.
 * 1. 동일한 viewModel 인스턴스가 모든 테스트에서 재사용된다는 의미인가요?
 * 2. 문제가 발생하지 않나요? 예를 들어 gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset 테스트 메서드 다음에 gameViewModel_Initialization_FirstWordLoaded 테스트 메서드가 실행되면 어떻게 되나요? 초기화 테스트가 실패하나요?
 * 두 질문에 대한 답은 '아니요'입니다. 테스트 메서드는 개별적으로 실행되어 변경 가능한 테스트 인스턴스 상태로 인한 예상치 못한 부작용을 방지합니다. 기본적으로 JUnit은 각 테스트 메서드가 실행되기 전에 테스트 클래스의 새 인스턴스를 만듭니다.
 * 지금까지 GameViewModelTest 클래스에 4개의 테스트 메서드가 있으므로 GameViewModelTest는 4번 인스턴스화됩니다. 각 인스턴스에는 viewModel 속성의 자체 사본이 있습니다. 따라서 테스트 실행 순서는 중요하지 않습니다.
 * 참고: 이러한 '메서드별' 테스트 인스턴스 수명 주기는 JUnit4부터 기본 동작입니다.
 */
class SettingViewModelTest {
    private val vm = SettingViewModel(
        ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json"))))

    /**
     * 참고: 위 코드는 thingUnderTest_TriggerOfTest_ResultOfTest 형식을 사용하여 테스트 함수 이름을 지정합니다.
     * thingUnderTest = settingViewModel
     * TriggerOfTest = ChangeConnectedDevicesScreenProfile
     * ResultOfTest = ChangedProfile
     */
    @Test
    fun settingViewModel_ChangeConnectedDevicesScreenProfile_ChangedProfile() {
        /*
        val bluetooth = vm.uiState.bluetooth
        val nfc = vm.uiState.nfc

        assertEquals(bluetooth, "ON")
        assertEquals(nfc, "ON")

        vm.changeBluetooth("true")
        vm.changeNfc("true")

        assertEquals(bluetooth, "true")
        assertEquals(nfc, "true")
         */
    }

    @Test
    fun settingViewModel_ChangeConnectedDevicesScreenProfile_CreateChangedProfileFile() {
        /*
        val bluetooth = vm.uiState.bluetooth
        val nfc = vm.uiState.nfc

        assertEquals(bluetooth, "ON")
        assertEquals(nfc, "ON")

        vm.changeBluetooth("true")
        vm.changeNfc("true")
        vm.save()

        val resultFile = File("/storage/emulated/0/Profile.json")
        assertEquals(resultFile.exists(),true)

        val result = resultFile.readText()
        val profileForTest = Gson().fromJson(result, Profile2::class.java)
        assertEquals(profileForTest.bluetooth, "true")
        assertEquals(profileForTest.nfc, "true")
         */
    }

    @Test
    fun settingViewModel_invokeSaveMethod_CreateProfileFile() {
        /*
        resultFile.delete()
        assertEquals(resultFile.exists(),false)
         */
        val resultFile = File("/storage/emulated/0/Profile.json")
        assertEquals(resultFile.exists(),true)
    }
}