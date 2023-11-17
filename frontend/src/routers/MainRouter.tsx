import { useSelector } from 'react-redux';
import { RootState } from '../state/store.ts';

export default function MainRouter() {
    const username = useSelector((state: RootState) => state.user.user);
    return <h1>Username: ${username.username}</h1>;
}
