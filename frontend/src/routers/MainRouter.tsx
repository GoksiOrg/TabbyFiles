import { useSelector } from 'react-redux';
import { type RootState } from '../state/store.ts';
import NavBar from '../components/navigation/NavBar.tsx';

export default function MainRouter() {
    const username = useSelector((state: RootState) => state.user.user);
    return (
        <>
            <NavBar />
            <h1>Username: ${username.username}</h1>
        </>
    );
}
