import React from 'react';
import ReactDOM from 'react-dom';
import App from './containers/App';
import reportWebVitals from './reportWebVitals';
import rootReducer from "./reducers";
import {applyMiddleware, compose, createStore} from 'redux'
import thunk from 'redux-thunk';
import {Provider} from 'react-redux'
import NotificationContainer from "react-notifications/lib/NotificationContainer";

const initialState = {
    authentication: {}
};

const store = createStore(
    rootReducer,
    initialState,
    compose(
        applyMiddleware(thunk),
        window.devToolsExtension ? window.devToolsExtension() : f => f
    )
);

ReactDOM.render(
    <Provider store={store}>
        <NotificationContainer/>
        <App/>
    </Provider>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
