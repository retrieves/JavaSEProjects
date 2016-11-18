package strategy.Sample2;
//��Ϊ�ӿ�
public interface FlyBehaviour {
	void fly();
}
class FlyNoWay implements FlyBehaviour{
	@Override
	public void fly() {
		System.out.println("�ҷɲ�����!");
	}
}
class FlyWithWings implements FlyBehaviour{
	@Override
	public void fly() {
		System.out.println("���ó���!");
	}
}
class FlyWithRocketPowered implements FlyBehaviour{
	@Override
	public void fly() {
		System.out.println("������������Ʒ���!");
	}
	
}