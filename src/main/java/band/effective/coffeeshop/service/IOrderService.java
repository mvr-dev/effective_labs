package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.CustomerOrder;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public interface IOrderService {
    Page<OrderResponseDTO> getAllOrders(int pageNumber, int pageSize);
    Optional<OrderResponseDTO> getOrderById(Long id);
    OrderResponseDTO updateOrder(long id,OrderRequestDTO customerOrder);
    OrderResponseDTO addOrder(OrderRequestDTO customerOrder);
    void deleteOrder(long id);
}
