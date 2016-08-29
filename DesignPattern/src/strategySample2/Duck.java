package strategySample2;
//Ѽ�ӵĳ���

//��������Ѽ�Ӷ���������Ϊswim��display
//���ǲ�ͬѼ�ӵ�fly��quack��Ϊ�ǲ�ͬ��
//���ԭ��1���ҳ�Ӧ���п�����Ҫ�仯֮���������Ƕ�����������Ҫ����ô����Ҫ�仯�Ĵ������һ��
//��fly��quack�����ɽУ�����Ϊ��ȡ����������һ�����������������Ϊ
//���ԭ��2����Խӿڱ�̣����������ʵ�ֱ��
//��Ϊ��ö���Ϊ�ӿڣ��ɲ�ͬ����Ϊ����ʵ����Ϊ�ӿڣ�������Duck������
//���ԭ��3��������ϣ����ü̳�
//Duck���г���fly��quack��Ϊ�ӿڣ������Ļ���Ϊ����Duck���޹���

//���Խ�Duck���Ϊ�ӿڣ������ͷ��뿪��ͬ��fly��quack��Ϊ�ˣ�ȱ�����޷�ʵ����ͬ�Ĵ��븴��
//����б仯�Ĳ��֣�����������ȥʵ�֣����綨����󷽷����������Ҫʵʱ�仯������֣����󷽷�������Ϊ���ˡ�
//����һ��Ѽ�ӵ����࣬����һ��ʼ������һ�ַ�ʽ���У��ֿ��Ըı����ķ��з�ʽ���ڲ��޸ļ��д����ǰ���£�
//����ǽӿ�������ȥʵ�֣���ôֻ����һ�ֲ��ԣ���Ҫ��һ�ֲ��Ծ���Ҫ�޸Ĵ���

public abstract class Duck {
	private FlyBehaviour flyBehaviour;
	private QuackBehaviour quackBehaviour;

	public FlyBehaviour getFlyBehaviour() {
		return flyBehaviour;
	}

	public void setFlyBehaviour(FlyBehaviour flyBehaviour) {
		this.flyBehaviour = flyBehaviour;
	}

	public QuackBehaviour getQuackBehaviour() {
		return quackBehaviour;
	}

	public void setQuackBehaviour(QuackBehaviour quackBehaviour) {
		this.quackBehaviour = quackBehaviour;
	}

	public Duck(FlyBehaviour flyBehaviour, QuackBehaviour quackBehaviour) {
		super();
		this.flyBehaviour = flyBehaviour;
		this.quackBehaviour = quackBehaviour;
	}

	public void swim() {
		System.out.println("���Ƕ�����Ӿ��");
	}

	public abstract void display();// ��ͬ��Ѽ�ӵ���ʾЧ����ͬ

	public void performFly() {
		flyBehaviour.fly();
	}

	public void performQuack() {
		quackBehaviour.quack();
	}
}
