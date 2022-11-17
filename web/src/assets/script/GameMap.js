import { BallObject } from "./BallObject";
import { Ball } from './Ball'
import { Circle } from "./Circle";
import { Baffle } from "./Baffle";
import { StraightPipe } from "./StraightPipe";
import { Rectangle } from "./Rectangle";
import { Triangle } from "./Triangle";


export class GameMap extends BallObject {
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0; //块的大小
        this.rows = 20;
        this.cols = 20;

        this.ball = null;
        this.circles = [];
    }

    start(){
        this.add_listening_events();
    }

    //获取当前鼠标位置
    getEventPosition(event){
        // console.log(this.L);
        let x = Math.floor(event.offsetX / this.L);
        let y = Math.floor(event.offsetY / this.L);
        return [x,y];
    }   
    
    /*
      在地图添加组件
      1、判别类别
      2、创建对象
      3、向后端发送消息
    */
    add(p){
        let type = this.store.state.layout.gameObject;
        // console.log(type);
        if(type === 'ball'){
            //球体只能有一个
            if(this.ball !== null) {
                console.log("destroy");
                this.ball.destroy();
            }
            this.ball = new Ball(this, p[0], p[1]);
            id = this.ball.id;
        } else if(type === 'Circle'){
            let circle = new Circle(this, p[0], p[1]);
            // this.circles.push(circle);
            id = circle.id;
        } else if(type === 'Blackhole'){
            //TODO(黑洞待实现)
        } else if(type === 'Rectangle') {
            let rectangle = new Rectangle(this, p[0], p[1]);
            id = rectangle.id
        } else if(type === 'Triangle'){
            let triangle = new Triangle(this, p[0], p[1]);
            id = triangle.id
        } else if(type === 'StraightPipe'){
            let pipe = new StraightPipe(this,p[0],p[1]);
            id = pipe.id;
        } else if(type === 'BendPipe'){
            //TODO(弯管道待实现)
        } else if(type ==='LeftBaffle'){
            if(this.leftBaffle !== null){
                this.store.state.layout.socket.send("delete Baffle " + this.leftBaffle.id);
                let old_p = [this.leftBaffle.c,this.leftBaffle.r];
                this.set_position(old_p ,false);
                this.leftBaffle.destroy();
            }
            p[0] = this.cols / 4;
            this.leftBaffle = new Baffle(this, p[0], p[1]);
            id = this.leftBaffle.id;
        } else if(type ==='RightBaffle'){
            if(this.rigthBaffle !== null){
                this.store.state.layout.socket.send("delete Baffle " + this.rigthBaffle.id);
                let old_p = [this.leftBaffle.c,this.leftBaffle.r];
                this.set_position(old_p,false);
                this.rigthBaffle.destroy();
            }
            p[0] = this.cols/ 4 * 3 ;
            this.rigthBaffle = new Baffle(this, p[0], p[1]);
            id = this.rigthBaffle.id;
        } else if(type === 'click'){
            //TODO(选中物体)
        } else {
            console.log("wrong type");
        }
 
        this.store.state.layout.socket.send("add "+ type + " " + p[0] + " " + p[1]);
    }



    //绑定事件
    add_listening_events(){ 
        if(this.store.state.layout.status === 'layout'){ //游玩模式绑定点击事件
           
            this.ctx.canvas.addEventListener("click",e =>{
                let p = this.getEventPosition(e);
                this.add(p);
            });
        } 
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    render() {
        const color_even = "#AAD751", color_odd = "#A2D149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
    }
}