import { Router } from 'express';
import * as ordersController from '../controllers/ordersControllers';

const router = Router();

router.get('/',ordersController.fetchAll);

export default router;