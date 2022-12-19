package control;

import model.Answer;
import model.Student;

public interface IRegistration extends java.rmi.Remote {
	public Student register(Student s) throws java.rmi.RemoteException;
	public Answer answer(Answer answer) throws java.rmi.RemoteException;
}
