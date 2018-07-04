import React from 'react';
import {UserVocableList} from "../component/UserVocableList.jsx";

export class UserListPage extends React.Component {
    constructor(props) {
        super(props);
        this.handleAdd = this.handleAdd.bind(this);
    }

    componentDidMount() {
        if (!this.props.user) return;
        this.loadUserList();
    }

    loadUserList() {
        fetch('/get-user-set/'.concat(this.props.user)).then(
            response => {
                if (response.ok) {
                    return response;
                }
                else {
                    return {}
                }
            }
        ).then(response => response.json())
            .then(currentWordList => {
                console.log(currentWordList);
                this.setState({userWordList: currentWordList})
            });
    }

    render() {
        if (!this.state) return null;
        const userWordList = this.state.userWordList;
        return userWordList && <div className={"userWordList"}>
            <button onClick={this.handleAdd}>Add Word</button>
            <br/><UserVocableList list={userWordList}/></div>;
    }

    handleAdd() {

    }
}