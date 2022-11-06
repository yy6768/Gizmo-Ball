
export default{
    state: {
        socket:null,
        gameMap:null,
        gameObject:null
    },
    getters: {

    },
    mutations: {
        updateMap(state,gameMap){
            state.gameMap = gameMap
        },
        updateGameObject(state, gameObject) {
            state.gameObject = gameObject;
        },
    },
    actions: {

    },
    modules: {
        
    }

}