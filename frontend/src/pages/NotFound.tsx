import { useNavigate } from 'react-router-dom';

export default function NotFound() {
    const navigate = useNavigate();
    return (
        <div className='flex-center flex-column vh-100'>
            <div className='position-relative'>
                <img src='/static/error_cat.png' alt='Error cat' style={{ maxWidth: '100%', height: 'auto' }} />
                <p className='display-4 position-absolute bottom-0 translate-middle start-83'>404</p>
            </div>
            <p className='h4 mb-4'>Requested resource can't be found !</p>
            <button
                className='btn btn-primary'
                onClick={() => {
                    navigate('/');
                }}
            >
                Return home
            </button>
        </div>
    );
}
