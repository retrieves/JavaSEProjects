package strategy.Sample2;
//��Ϊ�ӿ�
public interface QuackBehaviour {
	void quack();
}
class Quack implements QuackBehaviour{
	@Override
	public void quack() {
		System.out.println("����!");
	}
}
class MuteQuack implements QuackBehaviour{
	@Override
	public void quack() {
		System.out.println("�����~~~~");
	}
}
