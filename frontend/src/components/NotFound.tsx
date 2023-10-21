import { useNavigate } from 'react-router-dom';
/*TODO: variables not working*/
export default function NotFound() {
    const navigate = useNavigate();
    return (
        <div className='d-flex justify-content-center align-items-center flex-column vh-100'>
            <div className='position-relative'>
                <img src='/static/error_cat.png' alt='Error cat' style={{ maxWidth: '100%', height: 'auto' }} />
                <p className='display-4 position-absolute bottom-0 translate-middle' style={{ left: '83%' }}>
                    404
                </p>
            </div>
            <p className='h4 mb-4'>Requested resource can't be found !</p>
            <button
                className='btn btn-primary btn-block'
                onClick={() => {
                    navigate('/');
                }}
            >
                Return home
            </button>
        </div>
    );
}
