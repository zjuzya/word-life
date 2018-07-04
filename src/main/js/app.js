'use strict';

import {LoginBanner} from "./component/LoginBanner.jsx";
import {MemorizePage} from "./page/MemorizePage.jsx";
import {ChoosingSetPage} from "./page/ChoosingSetPage.jsx";
import {UserListPage} from "./page/UserListPage.jsx";

const React = require('react');
const ReactDOM = require('react-dom');

export class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedInUser: this.props.loggedInUser,
            // mode 1-memorize words
            // mode 2-choose set
            // mode 3-my word list
            mode: 1,
        };
        this.updateMode = this.updateMode.bind(this);
    }

    updateMode(newMode) {
        this.setState({mode: newMode});
    }

    componentDidMount() {
    }

    render() {
        return (
            <div>
                <LoginBanner user={this.state.loggedInUser}
                             mode={this.state.mode}
                             updateParentMode={this.updateMode}/>
                {this.state.mode === 1 && <MemorizePage user={this.state.loggedInUser}/>}
                {this.state.mode === 2 &&
                <ChoosingSetPage user={this.state.loggedInUser} changeMode={this.updateMode}/>}
                {this.state.mode === 3 && <UserListPage user={this.state.loggedInUser}/>}
            </div>
        )
    }

}

ReactDOM.render(
    <App loggedInUser={document.getElementById("username").innerHTML}
         user_id={document.getElementById("user_id")}/>,
    document.getElementById('react')
)