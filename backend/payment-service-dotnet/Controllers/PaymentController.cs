using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using PaymentService.Models;

namespace PaymentService.Controllers
{
    [ApiController]
    [Route("payment")]
    [Authorize] // JWT REQUIRED
    public class PaymentController : ControllerBase
    {
        [HttpPost("pay")]
        public IActionResult Pay([FromBody] PaymentRequest request)
        {
            if (request.Amount <= 0)
            {
                return BadRequest(new { status = "FAILED" });
            }

            return Ok(new { status = "SUCCESS" });
        }
    }
}
