import React from 'react';
import {UserVocable} from "./UserVocable.jsx";

export class UserVocableList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const vocables = this.props.list && <table>
            <tbody>{this.props.list.map(vocable => <UserVocable key={"word".concat(vocable.id)}
                                                            vocable={vocable.vocable}
                                                            description={vocable.description}/>)}</tbody>
        </table>;
        return vocables && <div>{vocables}</div>;
    }
}