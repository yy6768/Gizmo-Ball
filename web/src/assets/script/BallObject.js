

//所有渲染对象
const BALL_OBJECTS =[];
//对象的id
var base_id = 0;

// 基类
export class BallObject{
    constructor() {
        BALL_OBJECTS.push(this);
        this.timedelta = 0
        this.has_called_start = false;//是否已经执行过
        this.id = base_id;
        base_id++;
        console.log(base_id) 
    }
    // 只执行一次
    start() {

    }


    // 每一帧执行一次除了第一帧
    update() {

    }

    // 删除之前执行
    on_destroy() {

    }

    // 删除我的对象
    destroy() {
        this.on_destroy();
        // in 遍历的是下标，of遍历的是值
        for (let i in BALL_OBJECTS) {
            const obj = BALL_OBJECTS[i];
            if (obj === this) {
                BALL_OBJECTS.splice(i);
                break;
            }
        }
    }
}
// 上一个执行的时刻
let last_timestamp;

const step = timestamp => {
    for (let obj of BALL_OBJECTS) {
        if (!obj.has_called_start) {
            obj.has_called_start = true
            obj.start()
        } else {
            obj.timedelta = timestamp - last_timestamp;
            obj.update()
        }
    }
    last_timestamp = timestamp;
    // 继续实现下一帧的step函数
    requestAnimationFrame(step)
}
// 开始执行step函数
requestAnimationFrame(step)
