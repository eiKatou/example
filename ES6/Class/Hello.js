class Cat {
  constructor(name="名無し猫"){
    this.name = name;
  }
  set age(age) {
    this._age = age;
  }
  get age() {
    return this._age;
  }
  nake(){
    console.log(`${this.name}(${this.age}才)はニャーと鳴きました！`);
  }
  static jump() {
    console.log('ジャンプした');
  }
}

class BossCat extends Cat {
  nake(){
    super.nake();
    console.log(`さらに、ボスの${this.name}は、ニャニャニャと鳴きました！`);
  }
}

let mike = new Cat("ミケ");
mike.age = 5;
mike.nake();
Cat.jump();

let hana = new Cat();
hana.age = 10;
hana.nake();

let mikan = new BossCat("ミカン");
mikan.age = 2;
mikan.nake();
BossCat.jump();