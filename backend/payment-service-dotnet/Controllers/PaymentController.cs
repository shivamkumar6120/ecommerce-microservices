using Microsoft.AspNetCore.Mvc;
using PaymentService.Models;

namespace PaymentService.Controllers
{
    [ApiController]
    [Route("payment")]
    public class PaymentController : ControllerBase
    {
        [HttpPost("pay")]
        public IActionResult Pay([FromBody] PaymentRequest request)
        {
            // Simulate payment logic
            if (request.Amount <= 0)
            {
                return BadRequest(new { status = "FAILED" });
            }

            return Ok(new { status = "SUCCESS" });
        }
    }
}
