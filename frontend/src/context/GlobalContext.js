import React, {createContext} from "react";

const GlobalContext = createContext()
//this context to provide global state or func
function GlobalProvider({children}){
    //transform to vnd
    const convert = (number) => {
        return number.toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        })
    }

    const val  = {
        convert
    }

    return(
        <GlobalContext.Provider value={val}>
            {children}
        </GlobalContext.Provider>
    )
}

export {GlobalContext, GlobalProvider}