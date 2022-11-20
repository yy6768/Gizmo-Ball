import { GimzoObject } from "./GimzoObject";
import { Ball } from './Ball'
import { Circle } from "./Circle";
import { Baffle } from "./Baffle";
import { StraightPipe } from "./StraightPipe";
import { Rectangle } from "./Rectangle";
import { Triangle } from "./Triangle";
import { Blackhole } from "./Blackhole";
import { BendPipe } from "./BendPipe";


export class GameMap extends GimzoObject {
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0; //块的大小
        this.rows = 20;
        this.cols = 20;

        this.ball = null;
        this.leftBaffle = null;
        this.rigthBaffle = null;
        //记录每个格子放的对象id
        this.components = {};
    }

    start(){
        this.add_listening_events();
    }

    //获取当前鼠标位置
    getEventPosition(event){
        // console.log(this.L);
        let x = Math.floor(event.offsetX / this.L);
        let y = Math.floor(event.offsetY / this.L);
        return [x , y];
    }   
    

    //检查是否有物体重合
    check_position(p){
        return this.components[p] === undefined;
    }

    //设置物体已经重合
    set_position(p, id){
       this.components[p] = id;
    }
    
    /*
      在地图添加组件
      1、判别类别
      2、创建对象
      3、向后端发送消息
    */
    add(p){
        let type = this.store.state.layout.gameObject;
        let id = 0;
        // console.log(type);
        if(type === 'Ball'){
            //球体只能有一个
            if(this.ball !== null) {
                this.ball.destroy();
            }
            this.ball = new Ball(this, p[0], p[1]);
            id = this.ball.id;
        } else if(type === 'Circle'){
            let circle = new Circle(this, p[0], p[1]);
            id = circle.id;
        } else if(type === 'Blackhole'){
            let blackhole = new Blackhole(this, p[0], p[1]);
            id = blackhole.id;
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
            let bendPipe = new BendPipe(this, p[0], p[1]);
            id = bendPipe.id;
        } else if(type ==='LeftBaffle'){
            if(this.leftBaffle !== null){
                this.leftBaffle.destroy();
            }
            this.leftBaffle = new Baffle(this, p[0], p[1]);
            id = this.leftBaffle.id;
        } else if(type ==='RightBaffle'){
            if(this.rigthBaffle !== null){
                this.rigthBaffle.destroy();
            }
           
            this.rigthBaffle = new Baffle(this, p[0], p[1]);
            id = this.rigthBaffle.id;
        } else {
            console.log("wrong type");
        }
        this.store.state.layout.socket.send("add "+ type + " " + id +" "+ p[0] + " " + p[1]);
    }


    //绑定事件
    add_listening_events(){ 
        if(this.store.state.layout.status === 'layout'){ //游玩模式绑定点击事件
           
            this.ctx.canvas.addEventListener("click",e =>{
                let p = this.getEventPosition(e);
                let type = this.store.state.layout.gameObject;
                if(type === 'LeftBaffle') {
                    p[0] = this.cols / 4;
                } else if(type === 'RightBaffle'){
                    p[0] = this.cols/ 4 * 3 ;
                }
                if(this.store.state.layout.gameObject !== "click" && this.check_position(p)){
                    this.add(p);
                } else if(this.store.state.layout.gameObject === 'click'){
                    console.log("click", p);
                    this.store.commit("updateObject", this.components[p]);
                }
            });
        } 
    }

    update_size() {
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }


    next_step(){
        //TODO(游戏进行时每一步变化)
    }

    update() {
        this.update_size();
        if(this.store.state.layout.status === 'playing'){
            this.next_step();
        }
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