import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { type RootState } from '../../state/store';
import { faFolderOpen, faLock, faUser } from '@fortawesome/free-solid-svg-icons';
import NavDropdownItem from './NavDropdownItem';
import NavItem from './NavItem';
import logout from '../../api/logout';
import { useErrorBoundary } from 'react-error-boundary';
import { clearUser } from '../../state/user/actions/slice/userSlice';

export default function NavBar() {
    const { username, isAdmin } = useSelector((state: RootState) => state.user.user);
    const { showBoundary } = useErrorBoundary();
    const dispatch = useDispatch();
    const handleLogout = () => {
        logout()
            .then(() => {
                dispatch(clearUser());
                window.location.href = '/auth/login';
            })
            .catch(showBoundary);
    };
    return (
        <nav
            className='navbar navbar-expand-sm bg-body-secondary-tabby'
            aria-label='Main navigation'
            data-bs-theme='dark'
        >
            <div className='container-fluid'>
                <div className='d-flex justify-content-between'>
                    <div className='navbar-brand d-flex align-items-center'>
                        <Link to='/'>
                            <img src='static/maca_brand.png' height='9%' width='9%' alt='Tabby cat' />
                        </Link>
                    </div>
                    <button
                        className='navbar-toggler'
                        type='button'
                        data-bs-toggle='collapse'
                        data-bs-target='#navigation'
                        aria-controls='navigation'
                        aria-expanded='false'
                        aria-label='Toggle navigation'
                    >
                        <span className='navbar-toggler-icon'></span>
                    </button>
                </div>
                <div className='collapse navbar-collapse' id='navigation'>
                    <ul className='navbar-nav ms-auto list-unstyled mb-0 me-3'>
                        <NavDropdownItem icon={faUser} text={'User'}>
                            <p className='dropdown-item'>
                                Logged in as: <span className='link-info'>{username}</span>
                            </p>
                            <hr className='dropdown-divider' />
                            <button
                                className='dropdown-item'
                                onClick={() => {
                                    handleLogout();
                                }}
                            >
                                Logout
                            </button>
                        </NavDropdownItem>
                        {isAdmin && <NavItem icon={faLock} text='Admin area' path='/admin/' />}
                        <NavItem icon={faFolderOpen} text='Your files' path='/files/' />
                    </ul>
                </div>
            </div>
        </nav>
    );
}
