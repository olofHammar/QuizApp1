import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Denna klass använder jag för att kommunicera mellan fragment.
class Communicator : ViewModel(){

    val nrOfQuestions = MutableLiveData<Any>()

    fun sendNrOfQuestions(nr:Int){
        nrOfQuestions.setValue(nr)
    }
}